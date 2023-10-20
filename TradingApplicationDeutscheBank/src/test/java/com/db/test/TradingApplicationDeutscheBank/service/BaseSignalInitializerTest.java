package com.db.test.TradingApplicationDeutscheBank.service;

        import org.mockito.MockedStatic;
        import org.mockito.Mockito;

        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.util.List;

        import static org.mockito.Mockito.mockStatic;

public class BaseSignalInitializerTest {
    protected void mockFilesToRead(List<Path> files) {
        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.walk(Mockito.any()))
                    .thenReturn(files.stream());
        }
    }
}
