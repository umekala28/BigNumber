public class BigNumberTest {

    public static void main(String[] args) {
        // Test case: Large positive number addition
        BigNumber num1 = createBigNumber("123456789012345678901234567890");
        BigNumber num2 = createBigNumber("987654321098765432109876543210");
        testArithmeticOperations(num1, num2);

        // Test case: Large positive number subtraction
        num1 = createBigNumber("987654321098765432109876543210");
        num2 = createBigNumber("123456789012345678901234567890");
        testArithmeticOperations(num1, num2);

        // Test case: Large negative number addition
        num1 = createBigNumber("-123456789012345678901234567890");
        num2 = createBigNumber("-987654321098765432109876543210");
        testArithmeticOperations(num1, num2);

        // Test case: Large negative number subtraction
        num1 = createBigNumber("-987654321098765432109876543210");
        num2 = createBigNumber("-123456789012345678901234567890");
        testArithmeticOperations(num1, num2);

        // Test case: Positive number subtraction resulting in negative result
        num1 = createBigNumber("1234567890");
        num2 = createBigNumber("9876543210");
        testArithmeticOperations(num1, num2);

        // Test case: Negative number subtraction resulting in positive result
        num1 = createBigNumber("-9876543210");
        num2 = createBigNumber("-1234567890");
        testArithmeticOperations(num1, num2);

        // Test case: Zero division
        num1 = createBigNumber("0");
        num2 = createBigNumber("9876543210");
        testArithmeticOperations(num1, num2);

        // Test case: Division by zero
        num1 = createBigNumber("1234567890");
        num2 = createBigNumber("0");
        testArithmeticOperations(num1, num2);

        // Test case: Invalid input (non-numeric characters)
        num1 = createBigNumber("1234567a890");
        num2 = createBigNumber("9876543210");
        if (num1 != null && num2 != null) {
            testArithmeticOperations(num1, num2);
        } else {
            System.out.println("Invalid input: Non-numeric characters found");
        }
    }

    private static BigNumber createBigNumber(String numberString) {
        try {
            return new BigNumber(numberString);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input string format: " + numberString);
            return null;
        }
    }

    private static void testArithmeticOperations(BigNumber num1, BigNumber num2) {
        if (num1 == null || num2 == null) {
            System.out.println("Invalid operation: BigNumber objects are null");
            return;
        }

        System.out.println("Test case: " + num1 + " and " + num2);

        // Test addition
        BigNumber sum = num1.add(num2);
        System.out.println("Sum: " + sum);

        // Test subtraction
        BigNumber difference = num1.subtract(num2);
        System.out.println("Difference: " + difference);

        // Test multiplication
        BigNumber product = num1.multiply(num2);
        System.out.println("Product: " + product);

        // Test division
        if (num2.equalTo(new BigNumber("0"))) {
            System.out.println("Division by zero: Cannot calculate quotient");
        } else {
            BigNumber quotient = num1.divide(num2);
            System.out.println("Quotient: " + quotient);
        }

        // Test comparison
        System.out.println("Is num1 greater than num2? " + num1.greaterThan(num2));
        System.out.println("Is num1 less than num2? " + num1.lessThan(num2));
        System.out.println("Are num1 and num2 equal? " + num1.equalTo(num2));
        System.out.println();
    }
}

