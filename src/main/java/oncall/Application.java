package oncall;

import oncall.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig app = new AppConfig();
        app.run();
    }
}
