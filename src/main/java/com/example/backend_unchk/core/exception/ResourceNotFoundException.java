package com.example.backend_unchk.core.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ResourceNotFoundException(Long id, String resourceName) {
        super(resourceName + " avec l'ID " + id + " non trouvé");
    }
}
