import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid; // creates the grid
    private int openSites; // count of the open sites in the grid
    private int top; // imaginary top
    private int bottom; // imaginary bottom
    private WeightedQuickUnionUF wqu; // utilizes the algs4 WQU UF
    private int size; // size of the n-by-n grid


    // builds n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Input a valid n");
        wqu = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n][n];
        openSites = 0;
        top = n * n;
        bottom = n * n + 1;
        size = n;
    }

    // checks that the row and col submitted are inside the bounds of the n-by-n grid
    private void checkInBounds(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Input a valid row or column");
        }
        if (row > size - 1 || col > size - 1) {
            throw new IllegalArgumentException("Input a valid row or column");
        }
    }

    // returns ID location of the n-by-b box in the Union array
    private int getId(int row, int col) {
        return row * size + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        checkInBounds(row, col);

        if (!grid[row][col]) {
            grid[row][col] = true;
            openSites++;
        }

        // connects top row with imaginary top
        if (row == 0) {
            wqu.union(getId(row, col), top);
        }
        // connects bottom row with imaginary bottom
        if (row == size - 1) {
            wqu.union(getId(row, col), bottom);
        }
        // connects box with box on the left
        if (col != 0 && isOpen(row, col - 1)) {
            wqu.union(getId(row, col), getId(row, col - 1));


        }
        // connects box with box on the right
        if (col != size - 1 && isOpen(row, col + 1)) {
            wqu.union(getId(row, col), getId(row, col + 1));


        }
        // connects box with box beneath it
        if (row != 0 && isOpen(row - 1, col)) {
            wqu.union(getId(row, col), getId(row - 1, col));

        }
        // connects box with box above it
        if (row != size - 1 && isOpen(row + 1, col)) {
            wqu.union(getId(row, col), getId(row + 1, col));
        }
    }

    // returns if the selected box is open or not
    public boolean isOpen(int row, int col) {

        checkInBounds(row, col);

        if (!grid[row][col]) {
            return false;
        }
        else {
            return true;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        checkInBounds(row, col);

        if (wqu.find(getId(row, col)) == wqu.find(top)) {
            return true;
        }
        else {
            return false;
        }
    }

    // returns the number of open sites from the openSites global variable
    public int numberOfOpenSites() {
        return openSites;
    }

    // returns t/f if the current system percolates
    public boolean percolates() {
        if (wqu.find(bottom) == wqu.find(top)) {
            return true;
        }
        else {
            return false;
        }
    }

    // unit testings
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(0, 0);
        p.open(1, 0);
        p.open(2, 0);
        System.out.println("percolates = " + p.percolates());
        System.out.println("number of open sites = " + p.numberOfOpenSites());
    }
}


// Original Code that was improved

// private void unionifBounds(int row1, int col1, int row2, int col2) {
//     if (isOpen(row2, col2) == false && checkInBounds(row2, col2) == true) {
//         q.union(row1 * size + col1, row2 * size + col2);
//     }
//
// }

// if (row == 0) {
//     q.union(top, row * size + col);
//     if (row == 0 && col == 0) {
//         if (isOpen(row, col + 1)) {
//             q.union(row * size + col, row * size + (col + 1));
//         }
//         if (isOpen(row + 1, col)) {
//             q.union(row * size + col, (row + 1) * size + col);
//         }
//     }
//  top right corner
// else if (row == 0 && col == size - 1) {
//     if (isOpen(row, col - 1)) {
//         q.union(row * size + col, row * size + (col - 1));
//     }
//     if (isOpen(row + 1, col)) {
//         q.union(row * size + col, (row + 1) * size + col);
//     }
// }
//
// }
//
// if (row == size - 1) {
//     q.union(bottom, row * size + col);
//     if (row == size - 1 && col == 0) {
//         if (isOpen(row, col + 1)) {
//             q.union(row * size + col, row * size + (col + 1));
//         }
//         if (isOpen(row - 1, col)) {
//             q.union(row * size + col, (row - 1) * size + col);
//         }
//     }
//   bottom right corner
// else if (row == size - 1 && col == size - 1) {
//     if (isOpen(row, col - 1)) {
//         q.union(row * size + col, row * size + (col - 1));
//     }
//     if (isOpen(row - 1, col)) {
//         q.union(row * size + col, (row - 1) * size + col);
//     }
// }
// }
// if (col == 0 && (row != 0 || row != size - 1)) {
//     if (isOpen(row, col + 1)) {
//         q.union(row * size + col, row * size + (col + 1));
//     }
//     if (isOpen(row - 1, col)) {
//         q.union(row * size + col, (row - 1) * size + col);
//     }
//     if (isOpen(row + 1, col)) {
//         q.union(row * size + col, (row + 1) * size + col);
//     }
//
// }
// if (col == size - 1 && (row != 0 || row != size - 1)) {
//     if (isOpen(row, col - 1)) {
//         q.union(row * size + col, row * size + (col - 1));
//     }
//     if (isOpen(row - 1, col)) {
//         q.union(row * size + col, (row - 1) * size + col);
//     }
//     if (isOpen(row + 1, col)) {
//         q.union(row * size + col, (row + 1) * size + col);
//     }
//
// }
//
// if (isOpen(row, col - 1)) {
//     q.union(row * size + col, row * size + (col - 1));
// }
// if (isOpen(row, col + 1)) {
//     q.union(row * size + col, row * size + (col + 1));
// }
// if (isOpen(row - 1, col)) {
//     q.union(row * size + col, (row - 1) * size + col);
// }
// if (isOpen(row + 1, col)) {
//     q.union(row * size + col, (row + 1) * size + col);

//
// for (int i = 0; i < size; i++) {
// if (isOpen(0, i) == true) {
// q.union(top, i);
//}
//}
// for (int j = size ^ 2 - size; j < (size ^ 2 - 1); j++) {
// if (isOpen(row, col) == true) { //have to fix this
// q.union(bottom, j);
//}

//}
