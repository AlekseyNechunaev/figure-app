package ru.fc2.figure.commandline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fc2.figure.exception.CommandLineValidationException;
import ru.fc2.figure.printer.PrintMethod;
import ru.fc2.figure.utils.FileUtils;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandLineParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineParser.class);
    private static final String DEFAULT_OUTPUT_FILE_NAME = "result.txt";
    private static final int MAX_PARAMETERS = 3;
    private final Map<String, String> commandLineArguments;
    private final PrintMethod DEFAULT_PRINT_METHOD = PrintMethod.CONSOLE;


    public CommandLineParser(String[] commandLineArguments) {
        this.commandLineArguments = parse(commandLineArguments);
    }

    public CommandLineParametersInfo getParameters() {
        if (isValidArguments()) {
            final PrintMethod printMethod = getPrintMethod();
            final Path inputFilePath = getFilePath(commandLineArguments.get(CommandLineParameterNames.INPUT_FILE_PATH));
            if (!FileUtils.isExistFile(inputFilePath)) {
                LOGGER.error("the input file on the path does not exist: {}", inputFilePath);
                throw new CommandLineValidationException();
            }
            final boolean isExistSaveFilePath = commandLineArguments.containsKey(CommandLineParameterNames.SAVE_FILE_PATH);
            Path saveFilePath = null;
            if (printMethod == PrintMethod.CONSOLE && isExistSaveFilePath) {
                LOGGER.warn("the arguments printMethod=CONSOLE and saveFilePath are incompatible");
            }
            // Если путь до файла куда сохранять не передан, то умолчанию сохраняем в директорию с исходным файлом
            if (printMethod == PrintMethod.FILE && !isExistSaveFilePath) {
                saveFilePath = getFilePath(inputFilePath.getParent() + "/" + DEFAULT_OUTPUT_FILE_NAME);
            }
            if (printMethod == PrintMethod.FILE && isExistSaveFilePath) {
                saveFilePath = getFilePath(commandLineArguments.get(CommandLineParameterNames.SAVE_FILE_PATH));
            }
            LOGGER.info("printMethod = {}, inputFilePath = {}, saveFilePath = {}", printMethod, inputFilePath, saveFilePath);
            return new CommandLineParametersInfo(printMethod, inputFilePath, saveFilePath);
        }
        LOGGER.error("invalid command line arguments");
        throw new CommandLineValidationException();
    }

    private Map<String, String> parse(String[] args) {
        if (args.length == 0) {
            LOGGER.error("the path to the input data file is not specified");
            throw new CommandLineValidationException();
        }
        final Map<String, String> keyValueCommandLineArgs = new HashMap<>(args.length);
        for (String argument : args) {
            String[] splitKeyValue = argument.split("=");
            String key = splitKeyValue[0].substring(2);
            String value = splitKeyValue[1];
            keyValueCommandLineArgs.put(key, value);
        }
        LOGGER.info("parsed command line parameters {}", keyValueCommandLineArgs);
        return keyValueCommandLineArgs;
    }


    private PrintMethod getPrintMethod() {
        String printMethod = commandLineArguments.get(CommandLineParameterNames.PRINT_METHOD);
        if (printMethod == null) {
            return DEFAULT_PRINT_METHOD;
        }
        try {
            return PrintMethod.valueOf(printMethod.trim());
        } catch (IllegalArgumentException e) {
            LOGGER.error("an unknown printing method was transmitted", e);
            throw e;
        }
    }

    private Path getFilePath(String path) {
        return FileUtils.toAbsoluteAndNormalizePath(path);
    }

    private boolean isValidArguments() {
        final Set<String> commandLineKeys = commandLineArguments.keySet();
        final int inputParametersSize = commandLineKeys.size();
        if (inputParametersSize > MAX_PARAMETERS) {
            LOGGER.error("the number of passed parameters {} is greater than the number of allowed {} ",
                    inputParametersSize,
                    MAX_PARAMETERS);
            return false;
        }
        Set<String> otherParameters = commandLineKeys.stream()
                .filter(key -> !key.equals(CommandLineParameterNames.PRINT_METHOD) &&
                        !key.equals(CommandLineParameterNames.SAVE_FILE_PATH) &&
                        !key.equals(CommandLineParameterNames.INPUT_FILE_PATH))
                .collect(Collectors.toSet());
        if (!otherParameters.isEmpty()) {
            LOGGER.error("nonexistent parameters passed: {}", otherParameters);
            return false;
        }
        return true;
    }
}
