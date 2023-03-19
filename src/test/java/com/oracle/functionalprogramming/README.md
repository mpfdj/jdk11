# Supplier
A supplier is used when you want to generate or supply values without taking any input.


# Consumer and BiConsumer
You use a Consumer when you want to do something with a parameter but not return anything.
A BiConsumer is called with 2 parameters, they don't have to be the same type.


# Predicate and BiPredicate
Predicate is often used when filtering or matching


# Function and BiFunction
A Function is responsible for turning 1 parameter into a value of a potentially different type and returning it.


# UnaryOperator and BinaryOperator
UO and BO are a special case of a Function. They extend Function and BiFunction class. They require all type parameters to be the same type.