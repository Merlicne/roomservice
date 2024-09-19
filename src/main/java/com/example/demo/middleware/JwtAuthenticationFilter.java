// package com.example.demo.middleware;

// import java.io.IOException;

// import org.springframework.lang.NonNull;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import org.springframework.security.core.Authentication;


// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;

// import org.springframework.web.servlet.HandlerExceptionResolver;

// import com.example.demo.exception.UnAuthorizedException;


// @Component
// @RequiredArgsConstructor
// public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
//     private final HandlerExceptionResolver handlerExceptionResolver;

//     private final JwtService jwtService;
//     private final UserDetailsService userDetailsService;

//     @Override
//     protected void doFilterInternal(
//         @NonNull HttpServletRequest request,
//         @NonNull HttpServletResponse response,
//         @NonNull FilterChain filterChain
//     ) throws ServletException, IOException {
//         final String authHeader = request.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }
//     }
// }
