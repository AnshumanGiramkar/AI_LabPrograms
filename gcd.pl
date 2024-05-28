% Base cases
gcd(A, 0, A) :- A > 0.
gcd(0, B, B) :- B > 0.

% Recursive cases
gcd(A, B, X) :- A > B, gcd(B, A, X).
gcd(A, B, X) :- A =< B, T is B mod A, gcd(A, T, X).
