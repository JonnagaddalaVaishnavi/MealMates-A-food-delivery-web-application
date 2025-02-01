// Form validation
function validateForm(event) {
    event.preventDefault();
    
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const emailError = document.getElementById('email-error');
    const passwordError = document.getElementById('password-error');
    
    let isValid = true;
    
    // Reset error messages
    emailError.textContent = '';
    passwordError.textContent = '';
    
    // Email validation
    if (!email.value) {
        emailError.textContent = 'Email is required';
        isValid = false;
    } else if (!isValidEmail(email.value)) {
        emailError.textContent = 'Please enter a valid email address';
        isValid = false;
    }
    
    // Password validation
    if (!password.value) {
        passwordError.textContent = 'Password is required';
        isValid = false;
    } else if (password.value.length < 8) {
        passwordError.textContent = 'Password must be at least 8 characters long';
        isValid = false;
    }
    
    if (isValid) {
        // Here you would typically send the form data to a server
        console.log('Form submitted successfully');
        alert('Form submitted successfully!');
    }
    
    return isValid;
}

// Email validation helper
function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

// Password visibility toggle
function togglePassword() {
    const passwordInput = document.getElementById('password');
    const type = passwordInput.type === 'password' ? 'text' : 'password';
    passwordInput.type = type;
}

// Password strength checker
document.getElementById('password').addEventListener('input', function(e) {
    const password = e.target.value;
    const strengthIndicator = document.getElementById('password-strength');
    
    // Remove all classes first
    strengthIndicator.className = 'password-strength';
    
    if (password.length === 0) {
        return;
    }
    
    // Calculate password strength
    let strength = 0;
    
    // Length check
    if (password.length >= 8) strength += 1;
    
    // Character variety checks
    if (/[A-Z]/.test(password)) strength += 1;
    if (/[0-9]/.test(password)) strength += 1;
    if (/[^A-Za-z0-9]/.test(password)) strength += 1;
    
    // Update strength indicator
    if (strength <= 2) {
        strengthIndicator.classList.add('weak');
    } else if (strength === 3) {
        strengthIndicator.classList.add('medium');
    } else {
        strengthIndicator.classList.add('strong');
    }
});