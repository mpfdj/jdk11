see page 155

unbounded wildcard              List<?> l = new ArrayList<String>();
wildcard with upper bound       List<? extends Exception> l = new ArrayList<RuntimeException>();
wildcard with lower bound       List<? super Exception> l = new ArrayList<Object>();


see page 158
unbounded generics are immutable
upper-bounded generics are immutable
with generics you must pass the exact match