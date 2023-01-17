# Mars Rover Refactor

You have been paired with a craftsman-in-training to review their attempt at the Mars Rover kata. As a Craftsman, you
should have extensive knowledge of what makes good and bad software design. It is your job to pass on this knowledge to 
the next generation.

Your peer has followed test driven development and wrote a good suite of tests, but you can see glaring issues with 
their approach to the implementation. You must now work with them to improve the quality of the code.

As part of your pairing, you have provided the following hints help to get them started:

### Dependency Inversion

There are low level details mixed with high level policies. This will cause issues in the future when we want to swap
out the low level implementations, as we will have to modify files containing the high level code. We should only be 
modifying the high level code when the requirements change, not when the underlying technologies change.

Find a way to separate these out, ensuring that low level changes only occur in one place.

### Open/Closed

Your code is open for modification, and closed for extension. The opposite of what we would rather want, which is 
"open for extension, closed for modification".

Find a way to move logic branches away from the high level code. There are some design patterns suited to this
situation.

### Tight Coupling

The current implementation does not have good parent/child module relationships. This can be seen with the usage of 
static calls, and assembly in the Rover's construction. In the future, this can lead to inter-module dependency, meaning
that changes will have a rippling effect where it shouldn't. 

Find a way to change the code from high couping to low coupling.

### Mutability

There is a lot of data updating happening, mutability is dangerous when it's spread everywhere. It's hard to ensure 
responsibility of data changing when we can't easily track it, and this can lead to some nasty obscure bugs in the future. 

Either make the data immutable or find a design pattern to isolate the mutability. 

### Organisation

The project folder structure does not declare what kind of domain and subdomains we are working with, rather it 
declares what kind of low level relationships are currently used.

Ensure that your new project structure reflects the high level modules you have refactored to.

### Testing

While the current suite of tests is applicable for the current implementation, they will have to change for new changes.

Remember to follow test driven development where applicable during the refactor.