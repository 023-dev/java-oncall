package oncall.model.domain.worker;

import java.util.Collections;
import java.util.List;
import oncall.exception.custom.InvalidException;

public class Workers {
    private final List<Worker> workers;

    public Workers(List<Worker> workers) {
        validate(workers);
        this.workers = Collections.unmodifiableList(workers);
    }

    private void validate(List<Worker> workers) {
        if (workers.size() < 5 || workers.size() > 35) {
            throw new InvalidException("근무자는 2명부터 35명이어야 합니다.");
        }
        for (Worker worker : workers) {
            if (existWorker(worker)) {
                throw new InvalidException(worker.nickname() + "님이 두 번 편성되었어요.");
            }
        }
    }

    public boolean existWorker(Worker worker) {
        return workers.contains(worker);
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void swapWorkers(int index1, int index2) {
        Worker worker1 = workers.get(index1);
        Worker worker2 = workers.get(index2);
        workers.set(index1, worker2);
        workers.set(index2, worker1);
    }
}