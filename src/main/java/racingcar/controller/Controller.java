package racingcar.controller;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import racingcar.domain.Car;
import racingcar.service.GameService;
import racingcar.util.Parser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Controller {
    private GameService gameService;
    private Parser parser = new Parser();
    private OutputView outputView = new OutputView();

    public void run() {
        InputView.requestCarNamesMessage();
        List<String> carNames = parser.parseCarNames(Console.readLine());
        InputView.requestNumberOfTrialMessage();
        List<Car> cars = getCars(carNames);
        int trial = parser.parseNumberOfTrial(Console.readLine());
        gameService = new GameService(trial, cars);
        List<Car> winners = gameService.play();
        outputView.printResults(cars);
        outputView.printWinners(winners);
    }

    private List<Car> getCars(List<String> carNames) {
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < carNames.size(); i++) {
            carList.add(new Car(carNames.get(i)));
        }
        return carList;
    }
}