import java.util.Arrays;

public class BigNumber {
   
    private int[] digits;
    private boolean negative;

    // 1.Constructors:
    public BigNumber(String numberString) {
        // Initialize fields based on the input string
        parseNumberString(numberString);
    }
    private String resultToString(int[] result) {
        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            sb.append(digit);
        }
        return sb.toString();
    }
    // Methods (including 4. Handling invalid values):
    private void parseNumberString(String numberString) {
        if (numberString.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be empty");
        }

        if (numberString.charAt(0) == '-') {
            negative = true;
            if (numberString.length() == 1) {
                throw new IllegalArgumentException("Invalid input string format: " + numberString);
            }
            numberString = numberString.substring(1);
        }

        // Remove leading zeros
        int nonZeroIndex = 0;
        while (nonZeroIndex < numberString.length() - 1 && numberString.charAt(nonZeroIndex) == '0') {
            nonZeroIndex++;
        }
        numberString = numberString.substring(nonZeroIndex);

        if (numberString.isEmpty()) {
            
        	// If the string becomes empty after removing leading zeros, it represents zero
            digits = new int[]{0};
            negative = false;
            return;
        }

        for (char c : numberString.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Invalid character in input string: " + c);
            }
        }

        digits = new int[numberString.length()];
        for (int i = 0; i < numberString.length(); i++) {
            digits[i] = Character.getNumericValue(numberString.charAt(i));
        }
    }


// 2. Arithmetic Operations 
//Addition:
    public BigNumber add(BigNumber other) {
        if (this.negative != other.negative) {
            // If signs are different, perform subtraction instead
            if (this.negative) {
                return other.subtract(this.negate());
            } else {
                return this.subtract(other.negate());
            }
        }

        // Carry out addition for numbers with the same sign
        int maxLength = Math.max(this.digits.length, other.digits.length);
        int[] result = new int[maxLength + 1]; // Additional space for carry

        int carry = 0;
        for (int i = 0; i < maxLength || carry != 0; i++) {
            int sum = carry;
            if (i < this.digits.length) sum += this.digits[this.digits.length - 1 - i];
            if (i < other.digits.length) sum += other.digits[other.digits.length - 1 - i];
            result[result.length - 1 - i] = sum % 10;
            carry = sum / 10;
        }
        return new BigNumber(resultToString(result));
    }
//Subtraction:
    public BigNumber subtract(BigNumber other) {
        if (this.negative != other.negative) {
            // If signs are different, perform addition instead
            if (this.negative) {
                return other.add(this.negate());
            } else {
                return this.add(other.negate());
            }
        }

        int maxLength = Math.max(this.digits.length, other.digits.length);
        int[] result = new int[maxLength];

        int borrow = 0;
        for (int i = 0; i < maxLength; i++) {
            int diff = borrow;
            if (i < this.digits.length) diff += this.digits[this.digits.length - 1 - i];
            if (i < other.digits.length) diff -= other.digits[other.digits.length - 1 - i];
            if (diff < 0) {
                diff += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }
            result[result.length - 1 - i] = diff;
        }

        return new BigNumber(resultToString(result));
    }
//Multiplication:
    public BigNumber multiply(BigNumber other) {
        // Implement multiplication logic
        int m = this.digits.length;
        int n = other.digits.length;
        int[] result = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            int carry = 0;
            for (int j = n - 1; j >= 0; j--) {
                int temp = this.digits[i] * other.digits[j] + carry + result[i + j + 1];
                result[i + j + 1] = temp % 10;
                carry = temp / 10;
            }
            result[i] += carry;
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            sb.append(digit);
        }

        return new BigNumber(sb.toString());
    }

//Division:
    public BigNumber divide(BigNumber other) {
        BigNumber quotient = new BigNumber("0");
        BigNumber dividend = this.abs();
        BigNumber divisor = other.abs();

        while (dividend.greaterThanOrEqual(divisor)) {
            BigNumber temp = divisor;
            int count = 1;
            while (dividend.greaterThanOrEqual(temp)) {
                dividend = dividend.subtract(temp);
                quotient = quotient.add(new BigNumber("1"));
                temp = temp.add(divisor);
                count++;
            }
        }

        // Adjusting sign of quotient
        if (this.negative != other.negative) {
            quotient = quotient.negate();
        }
        
        // Check if all digits of quotient are zero
        boolean allZero = true;
        for (int digit : quotient.digits) {
            if (digit != 0) {
                allZero = false;
                break;
            }
        }
        if (allZero) {
            quotient.negative = false; 
        }

        return quotient;
    }

//3.Comparison Operators:
    private int compare(BigNumber other) {
        if (this.negative && !other.negative) return -1;
        if (!this.negative && other.negative) return 1;
        if (this.negative && other.negative) {
            if (this.digits.length < other.digits.length) return 1;
            if (this.digits.length > other.digits.length) return -1;
            for (int i = 0; i < this.digits.length; i++) {
                if (this.digits[i] < other.digits[i]) return 1;
                if (this.digits[i] > other.digits[i]) return -1;
            }
            return 0;
        } else {
            if (this.digits.length < other.digits.length) return -1;
            if (this.digits.length > other.digits.length) return 1;
            for (int i = 0; i < this.digits.length; i++) {
                if (this.digits[i] < other.digits[i]) return -1;
                if (this.digits[i] > other.digits[i]) return 1;
            }
            return 0;
        }
    }

    public boolean greaterThan(BigNumber other) {
        return compare(other) > 0;
    }

    public boolean lessThan(BigNumber other) {
        return compare(other) < 0;
    }

    public boolean equalTo(BigNumber other) {
        return compare(other) == 0;
    }
    public BigNumber abs() {
        return new BigNumber(this.toString().replace("-", ""));
    }

    public boolean greaterThanOrEqual(BigNumber other) {
        return compare(other) >= 0;
    }

    public BigNumber negate() {
        return new BigNumber(this.toString().startsWith("-") ? this.toString().substring(1) : "-" + this.toString());
    }

    // toString method to print BigNumber object
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (negative)
            sb.append("-");
        for (int digit : digits) {
            sb.append(digit);
        }
        return sb.toString();
    }

}