package oncall.config;

import oncall.controller.AppController;
import oncall.view.input.InputView;
import oncall.view.output.OutputView;

public class AppConfig {
    private final AppController appController;

    public AppConfig() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        this.appController = new AppController(inputView, outputView);
    }

    public void run() {
        appController.run();
    }
}
