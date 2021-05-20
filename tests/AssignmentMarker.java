import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.platform.engine.discovery.DiscoverySelectors.*;

public class AssignmentMarker {
    public static HashMap<String, Float> marks_success = new HashMap<>();
    public static HashMap<String, Float> marks_failed = new HashMap<>();
    SummaryGeneratingListener listener = new SummaryGeneratingListener();

    public void runOne(String className) throws ClassNotFoundException {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(Class.forName(className)))
                .build();
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        var am = new AssignmentMarker();
        var testFiles = new ArrayList<String>();
        var tests = new HashMap<String, Stream<Map.Entry<String, Float>>>();
        var results = new HashMap<String, Double>();

        try (Stream<Path> paths = Files.walk(Paths.get("."))) {
            var pathList = paths
                    .filter(f -> f.getFileName().toString().contains("Test.java"))
                    .collect(Collectors.toCollection(ArrayList::new));
            for (var p : pathList) {
                var name = p.getFileName().toString();
                testFiles.add(name.replace(".java", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (var test : testFiles) {
            am.runOne(test);
            var summary = am.listener.getSummary();
            summary.printFailuresTo(new PrintWriter(System.out));
            var successes = marks_success.entrySet().stream();
            var failures = marks_failed.entrySet().stream();
            tests.put(test + "_successes", successes);
            tests.put(test + "_failures", failures);
            var gained = marks_success.values().stream()
                    .mapToDouble(v -> v)
                    .sum();
            var lost = marks_failed.values().stream()
                    .mapToDouble(v -> v)
                    .sum();
            results.put(test + "_gained", gained);
            results.put(test + "_lost", lost);
            marks_success = new HashMap<>();
            marks_failed = new HashMap<>();
        }

        System.out.println("\nSuccesses:");
        System.out.println("-----------------");
        for (var test : testFiles) {
            tests.get(test + "_successes")
                    .forEach(success -> System.out.printf("%-60s | Marks: %.2f\n", test + ": " + success.getKey(), success.getValue()));
        }
        System.out.println("-----------------");

        System.out.println("\nFailures:");
        System.out.println("-----------------");
        for (var test : testFiles) {
            tests.get(test + "_failures")
                    .forEach(failure -> System.out.printf("%-60s | Marks: %.2f%n", test + ": " + failure.getKey(), failure.getValue()));
        }
        System.out.println("-----------------");

        System.out.println("\nMark summary:");
        double total_gained = 0;
        double total_lost = 0;
        for (var test : testFiles) {
            var gained = results.get(test + "_gained");
            total_gained += gained;
            var lost = results.get(test + "_lost");
            total_lost += lost;
            System.out.printf("\t%s:  [gained " + gained + ", lost " + lost + "]%n", test.replace("Test", ""));
        }
        System.out.println("Total: [" + (total_gained) + ", lost " + (total_lost) +"] (out of: " + (total_gained + total_lost) + ")");
        System.out.println("End of automated marking");
    }
}
