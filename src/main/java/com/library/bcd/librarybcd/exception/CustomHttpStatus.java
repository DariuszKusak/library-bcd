package com.library.bcd.librarybcd.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomHttpStatus {
    BOOK_ALREADY_BORROWED(4441, "BOOK_ALREADY_BORROWED"),
    BOOK_NOT_FOUND(4442, "BOOK_NOT_FOUND"),
    BOOK_LIMIT_EXTENDED(4444, "BOOK_LIMIT_EXTENDED"),
    USER_NOT_FOUND_EXCEPTION(4443, "USER_WITH_PASSWORD_NOT_EXISTS"),
    LOGIN_ALREADY_TAKEN(4444, "LOGIN_ALREADY_TAKEN");

    private final int value;
    private final String reasonPhrase;
}
