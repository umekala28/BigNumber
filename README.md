# BigNumber

A custom Java implementation for handling arbitrarily large integers with support for basic arithmetic operations, comparisons, and utility methods. This class is designed to mimic the functionality of Java's built-in BigInteger class while offering a simplified approach to understanding how large numbers can be handled programmatically.

## Features

Handles arbitrarily large integers (positive and negative) using an array-based representation.

### Basic arithmetic operations:
Addition
Subtraction
Multiplication
Division

### Comparison operations:
Greater than
Less than
Equal to

### Utility methods:
Absolute value
Negation
Conversion to string
Error handling for invalid inputs and edge cases like division by zero.

## How It Works

### The BigNumber class:

Stores digits of the number in an integer array.
Handles operations digit-by-digit, similar to manual arithmetic.
Supports both positive and negative numbers using a boolean negative flag.

## Class Methods

### Constructor

#### BigNumber(String numberString)
Creates a BigNumber from a numeric string.
Validates the input and handles negative signs and leading zeros.

### Arithmetic Operations

#### BigNumber add(BigNumber other)
Adds two BigNumber objects.
#### BigNumber subtract(BigNumber other)
Subtracts one BigNumber from another.
#### BigNumber multiply(BigNumber other)
Multiplies two BigNumber objects.
#### BigNumber divide(BigNumber other)
Divides one BigNumber by another. (Handles division by zero gracefully.)

### Comparison Methods
#### boolean greaterThan(BigNumber other)
#### boolean lessThan(BigNumber other)
#### boolean equalTo(BigNumber other)

### Utility Methods

#### BigNumber abs():  
Returns the absolute value of the number.
#### BigNumber negate(): 
Negates the number (changes the sign).
#### String toString(): 
Returns a string representation of the number.

## Installation

Clone the repository:
git clone https://github.com/your-repo/BigNumber.git
Navigate to the project directory and compile the code:
javac BigNumber.java BigNumberTest.java
Run the tests:
java BigNumberTest

## Usage

Here is a simple example demonstrating how to use the BigNumber class:

        BigNumber num1 = new BigNumber("12345678901234567890");
        BigNumber num2 = new BigNumber("-98765432109876543210");

        BigNumber sum = num1.add(num2);
        BigNumber difference = num1.subtract(num2);
        BigNumber product = num1.multiply(num2);

        System.out.println("Sum: " + sum);            // Output: Sum: -86419753208641975320
        System.out.println("Difference: " + difference); // Output: Difference: 111111111011111111100
        System.out.println("Product: " + product);    // Output: Product: -1219326311370217952237463801111263526900
    }
}
## Testing

The BigNumberTest class includes a variety of test cases:

Large positive and negative numbers.
Edge cases like zero and invalid input.
Arithmetic and comparison operations.
Limitations


## Contributing
Contributions are welcome! If you'd like to improve the BigNumber class or add new features, feel free to fork the repository and submit a pull request.

