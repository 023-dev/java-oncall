package oncall.model.domain.worker;

import oncall.exception.custom.InvalidException;

public record Worker(String nickname) {
    public Worker(String nickname) {
        this.nickname = validate(nickname);
    }

    private String validate(String nickname) {
        if (nickname.length() >= 5) {
            throw new InvalidException(nickname + "님의 닉네임을 5자 이하로 줄여야 합니다.");
        }
        if (nickname.isBlank()) {
            throw new InvalidException("닉네임을 입력해주세요.");
        }
        return nickname;
    }
}
