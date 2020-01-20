/**
 * Simple class representing a 7-digit phone number in the form "XXX-XXXX" for a
 * phone in the immediate OSU area.
 */
public class PhoneNumber {

    /**
     * The phone number representation.
     */
    private String rep;

    /**
     * Constructor. {@code pNum} must be in the form "XXX-XXXX" where each "X"
     * is a digit '0'-'9'.
     */
    public PhoneNumber(String pNum) {
        this.rep = pNum;
    }

    @Override
    public int hashCode() {
        String num = this.rep;
        int sum = 0;
        for (int i = 4; i < num.length(); i++) {
            int numerical = 0;
            ;
            if (!Character.isDigit(num.charAt(i))) {
                numerical = Character.digit(num.charAt(i), 36) % 10;
            } else {
                numerical = Character.digit(num.charAt(i), 10);
            }
            sum = sum + numerical;
        }
        return sum;
    }

}
