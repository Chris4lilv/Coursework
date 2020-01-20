import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Put your name here
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();

        if (bottom.compareTo(new NaturalNumber2(0)) != 0) {
            view.updateRootAllowed(true);
            view.updateDivideAllowed(true);
        } else {
            view.updateRootAllowed(false);
            view.updateDivideAllowed(false);
        }

        if (bottom.compareTo(top) > 0) {
            view.updateSubtractAllowed(false);
        } else {
            view.updateSubtractAllowed(true);
        }

        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.copyFrom(bottom);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        bottom.add(top);
        top.clear();

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        NaturalNumber temp = top.newInstance();
        temp.copyFrom(top);
        temp.subtract(bottom);
        top.transferFrom(temp);
        bottom.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        bottom.multiply(top);
        top.clear();

        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        NaturalNumber remainder = top.divide(bottom);

        bottom.copyFrom(top);
        top.transferFrom(remainder);

        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processPowerEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        NaturalNumber temp = bottom.newInstance();

        temp.copyFrom(bottom);

        power(top, temp);
        bottom.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        root(top, bottom.toInt());
        bottom.transferFrom(top);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {
        NaturalNumber bottom = this.model.bottom();
        bottom.multiplyBy10(digit);

        updateViewToMatchModel(this.model, this.view);

    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        NaturalNumber lowEnough = new NaturalNumber1L(0);
        NaturalNumber tooHigh = new NaturalNumber1L(n);
        NaturalNumber one = new NaturalNumber1L(1);
        tooHigh.add(one);
        //construct an object counting the difference of tooHigh and lowEnough
        NaturalNumber difference = new NaturalNumber1L(tooHigh);
        difference.subtract(lowEnough);
        while ((difference.compareTo(one) > 0)) {
            NaturalNumber guess = new NaturalNumber1L(tooHigh);
            guess.add(lowEnough);
            guess.divide(new NaturalNumber1L(2));
            //construct a new object for computing the power
            NaturalNumber guessNew = new NaturalNumber1L(guess);
            guessNew.power(r);
            if (guessNew.compareTo(n) > 0) {
                tooHigh.copyFrom(guess);

            } else {
                lowEnough.copyFrom(guess);
            }
            //update the difference of tooHigh and lowEnough
            difference.copyFrom(tooHigh);
            difference.subtract(lowEnough);
        }

        //update n
        n.copyFrom(lowEnough);

    }

    /**
     * Power method of NaturalNumber
     *
     * @param n
     * @param p
     */
    public static void power(NaturalNumber n, NaturalNumber p) {

        if (p.compareTo(new NaturalNumber1L(0)) == 0) {
            n.setFromInt(1);
        } else {
            NaturalNumber nCopy = new NaturalNumber1L(n);
            NaturalNumber pCopy = new NaturalNumber1L(p);

            int remainder = n.divideBy10();

            if (remainder % 2 == 0) {
                n.multiplyBy10(remainder);
                pCopy.divide(new NaturalNumber1L(2));
                power(nCopy, pCopy);
                nCopy.multiply(n);
            } else {
                n.multiplyBy10(remainder);
                //decrement p so that it will become even
                pCopy.decrement();
                power(nCopy, pCopy);
                nCopy.multiply(n);
            }

            n.copyFrom(nCopy);
        }

    }

}
