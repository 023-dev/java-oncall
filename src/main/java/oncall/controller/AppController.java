package oncall.controller;

import oncall.view.input.InputView;
import oncall.view.output.OutputView;

public class AppController {
    private final InputView input;
    private final OutputView output;


    public AppController(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    public void run() {

    }
}
