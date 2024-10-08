package com.komanrudden.multiauthapi.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * Meta-annotation to indicate that a method parameter should be bound to the current authenticated user.
 * <p>
 * This annotation can be applied to method parameters or types.
 * It is a composite annotation that combines `@AuthenticationPrincipal` with additional metadata.
 * </p>
 *
 * <p>Usage:</p>
 * <pre>
 * {@code
 * @GetMapping("/me")
 * public ResponseEntity<User> getCurrentUser(@CurrentUser User user) {
 *     return ResponseEntity.ok(user);
 * }
 * }
 * </pre>
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
