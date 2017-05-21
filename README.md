**Problem description**

Some things in supermarkets have simple prices: this can of beans costs $0.65. Other things have more complex prices. For example:

    three for a dollar (so what’s the price if I buy 4, or 5?)
    $1.99/pound (so what does 4 ounces cost?)
    buy two, get one free (so does the third item have a price?)

The exercise is to experiment with various models for representing money and prices that are flexible enough to deal with these (and other) pricing schemes, and at the same time are generally usable (at the checkout, for stock management, order entry, and so on). Spend time considering issues such as:

    does fractional money exist?
    when (if ever) does rounding take place?
    how do you keep an audit trail of pricing decisions (and do you need to)?
    are costs and prices the same class of thing?
    if a shelf of 100 cans is priced using “buy two, get one free”, how do you value the stock?

Keeping it simple and to the point, no web, persistence, ORM layers or frameworks.

Have to refactor 
- promotional item insertion to allow meal deal + other deals requiring x types of products
- each strategy will log an audit of pricing decisions made