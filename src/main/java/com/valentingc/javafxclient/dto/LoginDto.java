package com.valentingc.javafxclient.dto;

import java.util.UUID;

/**
 * Demo Login DTO - used for demonstratory login.
 * 
 * @author Valentin Goronjic
 */
public class LoginDto {
    private final UUID id;
    private final String username;

    private LoginDto(LoginDtoBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
    }

    public static class LoginDtoBuilder {
        private UUID id;
        private String username;

        public LoginDtoBuilder() {
            // might not be able to provide an id every time
        }

        public LoginDtoBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public LoginDtoBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public LoginDto build() {
            return new LoginDto(this);
        }
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
