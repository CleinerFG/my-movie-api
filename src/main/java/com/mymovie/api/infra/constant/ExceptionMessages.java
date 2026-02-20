package com.mymovie.api.infra.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessages {

    public static final String USERNAME_OR_PASSWORD_INVALID = "auth.username_or_password_invalid";
    public static final String VALIDATION_FAILED = "validation.failed";
    public static final String RESOURCE_NOT_FOUND = "resource.not_found";
    public static final String MOVIE_NOT_FOUND = "movie.not_found";
    public static final String CATEGORY_NOT_FOUND = "category.not_found";
    public static final String STREAMING_NOT_FOUND = "streaming.not_found";
}
