package id.ac.ui.cs.advprog.toytopiaorder.service;

import id.ac.ui.cs.advprog.toytopiaorder.model.UserResponse;

import java.util.concurrent.CompletableFuture;

public interface UserService {
    CompletableFuture<UserResponse> getUsernameWithToken(String token);
    CompletableFuture<Boolean> isAdmin(String token);
    CompletableFuture<String> getEmail(String token);
}