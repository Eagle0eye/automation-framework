package utils;

import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;

public class AllureUtils {
    private static final Logger log = LoggerFactory.getLogger(AllureUtils.class);
    public static final String ALLURE_RESULTS_PATH = "allure-results";

    // Moved from TestListener
    public static void cleanAllureResultsDirectory() {
        Path resultsPath = Paths.get(ALLURE_RESULTS_PATH);
        if (!Files.exists(resultsPath)) {
            log.info("Allure results directory does not exist, nothing to clean");
            return;
        }

        log.info("Cleaning Allure results directory...");
        try (Stream<Path> paths = Files.list(resultsPath)) {
            paths.forEach(path -> {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    log.warn("Failed to delete file: {}", path, e);
                }
            });
        } catch (IOException e) {
            log.error("Error cleaning Allure results directory", e);
        }
    }

    // Moved from TestListener
    public static void createAllureResultsDirectory() {
        Path resultsPath = Paths.get(ALLURE_RESULTS_PATH);
        if (!Files.exists(resultsPath)) {
            try {
                Files.createDirectories(resultsPath);
                log.info("Created Allure results directory successfully");
            } catch (IOException e) {
                log.error("Failed to create Allure results directory", e);
            }
        }
    }

    // Moved from TestListener
    public static void logAllureResultsStatus() {
        Path resultsPath = Paths.get(ALLURE_RESULTS_PATH);
        if (Files.exists(resultsPath)) {
            try {
                long fileCount = Files.list(resultsPath).count();
                log.info("Allure results directory contains {} files", fileCount);
            } catch (IOException e) {
                log.error("Error counting Allure result files", e);
            }
        } else {
            log.warn("Allure results directory does not exist");
        }
    }

    // Existing Allure report generation
    public static void generateAndOpenAllureReport() {
        try {
            if (!isAllureResultsAvailable()) {
                log.warn("No Allure results found in {}", ALLURE_RESULTS_PATH);
                return;
            }

            ProcessBuilder builder = new ProcessBuilder("allure", "serve", ALLURE_RESULTS_PATH);
            builder.inheritIO();
            Process process = builder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                log.info("Allure report opened successfully in browser");
            } else {
                log.error("Allure report generation failed with exit code {}", exitCode);
            }
        } catch (Exception e) {
            log.error("Error while generating Allure report", e);
        }
    }

    private static boolean isAllureResultsAvailable() {
        Path resultsPath = Paths.get(ALLURE_RESULTS_PATH);
        if (!Files.exists(resultsPath)) {
            return false;
        }

        try {
            return Files.list(resultsPath).findAny().isPresent();
        } catch (IOException e) {
            log.error("Error checking Allure results directory", e);
            return false;
        }
    }

    // Existing JSON attachment utility
    public static void attachJsonSchema(String resourcePath, String attachmentName) {
        try (InputStream schemaStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (schemaStream != null) {
                Allure.addAttachment(attachmentName, "application/json", schemaStream, ".json");
                log.info("Attached JSON schema '{}' as '{}'", resourcePath, attachmentName);
            } else {
                log.warn("JSON schema resource not found: {}", resourcePath);
            }
        } catch (IOException e) {
            log.error("Error attaching JSON schema", e);
        }
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}