/* *****************************************************************************
 *  Operating system: IntelliJ on Mac
 *  Compiler: IntelliJ
 *  Text editor / IDE: IntelliJ
 *
 *  Have you taken (part of) this course before: No
 *  Have you taken (part of) the Coursera course Algorithms, Part I or II: No
 *
 *  Hours to complete assignment (optional): 9 hours
 *
 **************************************************************************** */

Programming Assignment 1: Percolation


/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */

Set up a 2d array of booleans that were iniated to false. We set up an integer called
openSites that would update with the number of open sites there were.  Set up two integers
that were the top and bottom that simulated the virtual top and bottom of the grid.
We set up a integer variable called size that kept track of the size of the grid.
Lastly, we set up a object of the WeightedQuickUnionUF class that was used to union,
find, and see if certain sites were connected.

/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(row,col): First we check that the input is inbounds. Then we increased the count for
our opensquares global variable. Next we linked all the top row squares to the
imaginary top, and did the same with the bottoms one to an imaginary bottom. Next,
we worked on unioning a block to the top, bottom, left, and right of a square.
Keeping in mind the corner cases and bounds.

isOpen(row,col): First, we checked to see that the input is inbounds, and to throw an illegal
argument if not. Then, we used an if statement to check the input row and column
in the grid to see if it is open or not. It then returns a true or false statement
based on that current spot in the grid

isFull(row,col): We first checked that the input is in bounds. Next, we convert the input into
it's 2d ID, then we check to see if that ID is connected to the imaginary top
that we created earlier. And if it is connected it means that the current square
is full.

numberOfOpenSites(): To do this method we created a global integer variable at
the top of our code and added to the count of it in the open method.

percolates(): This method checks if the imaginary bottom and the imaginary top
are unioned and are connected. It returns true if they are connected or false
otherwise.

checkInBounds(row,col): This checks if the specified row and column are in the bounds
of the grid. If they aren't, then it throws and exception.

getId(row,col): This converts a 2d array number into a 1d array number.

/* *****************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for various values of n and T when implementing
 *  Percolation.java with QuickFindUF.java (not QuickUnionUF.java). Use a
 *  "doubling" hypothesis, where you successively increase either n or T by
 *  a constant multiplicative factor (not necessarily 2).
 *
 *  To do so, fill in the two tables below. Each table must have 5-10
 *  data points, ranging in time from around 0.25 seconds for the smallest
 *  data point to around 30 seconds for the largest one. Do not include
 *  data points that take less than 0.25 seconds.
 **************************************************************************** */

(keep T constant)
 T = 100
 multiplicative factor (for n) = 1.5

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
50          .251                ---             ---
75          1.065               4.243           3.564
113         4.717               4.429           3.67
169         21.323              4.520           3.72
253         106.505             4.995           3.967


(keep n constant)
 n = 100
 multiplicative factor (for T) = 2

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
10          .337                ----            ---
20          1.016               3.015          1.592
40          1.686               1.659          .730
80          2.642               1.567          .648
160         6.783               2.567          1.36
320         10.252              1.5114         .59588
640         15.432              1.505          .5897
1280        36.062              2.337           1.224


/* *****************************************************************************
 *  Using the empirical data from the above two tables, give a formula
 *  (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as function of both n and T, such as
 *
 *       ~ 5.3*10^-8 * n^5.0 * T^1.5
 *
 *  Briefly explain how you determined the formula for the running time.
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Use two significant figures for each coefficient and exponent
 *  (e.g., 5.3*10^-8 or 5.0).
 **************************************************************************** */

QuickFindUF running time (in seconds) as a function of n and T:

    ~ .0165 * n^3.65 * T^1
       _______________________________________



/* *****************************************************************************
 *  Repeat the previous two questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 **************************************************************************** */

(keep T constant)
 T = 100
 multiplicative factor (for n) = 2

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
200         .336                ---             ---
400         1.099               3.271           1.709
800         4.661               4.241           2.084
1600        27.487              5.897           2.56
3200        203.6               7.407           2.889


(keep n constant)
 n = 100
 multiplicative factor (for T) = 2

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
400         .289                 ----           ----
800         1.183                4.093          2.033
1600        1.726                1.459          .544
3200        3.743                2.169          1.117
6400        5.708                1.525          .609
12800       14.439               2.529          1.339
25600       22.244               1.541          .624
51200       45.66                2.053          1.038


WeightedQuickUnionUF running time (in seconds) as a function of n and T:

    ~ 7.408 * 10^-4 * n^2.2 * T^1
       _______________________________________



/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */

Backwash is a known bug that wrongly asseses a site to be full even though it isn't.


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
TA help from Daruis, Jack, and Nina

/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */

The only serious problem was that our standard deviation was incorrect.  We fixed this by
instanstiating a new PercolationStats object every iteration of the for loop.


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */

My partner and I enjoyed doing this assignment together.
