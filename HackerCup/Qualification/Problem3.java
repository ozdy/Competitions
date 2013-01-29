import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.Writer;
import java.math.BigInteger;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Problem3 {
	public static void main(String[] args) {
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("facebook13.in");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		OutputStream outputStream;
		try {
			outputStream = new FileOutputStream("facebook13.out");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		InputReader in = new InputReader(inputStream);
		OutputWriter out = new OutputWriter(outputStream);
		facebook13 solver = new facebook13();
		solver.solve(1, in, out);
		out.close();
	}
}

class facebook13 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int C = in.readInt();
        for(int cases=1;cases<=C;cases++) {
            long n = in.readInt(), k = in.readInt();
            long a = in.readInt(), b = in.readInt(), c = in.readInt(), r = in.readInt();
            long[] m = new long[(int)k];
            m[0] = a;
            for(int i=1;i<k;i++) {
                m[i] = (b*m[i-1] + c) % r;
            }
            long[] pos = new long[(int)(k+1)];
            Arrays.fill(pos,-1);
            for(int i=0;i<k;i++) {
                if(m[i]<=k)pos[(int)m[i]] = i;
            }
            long[] next = new long[(int)(k+2)];
            Arrays.fill(next,-1);
            int nextEmpty = 0;
            for(int i=0;i<=k;i++) {
                if(pos[i]==-1 || pos[i]< nextEmpty) {
                    next[nextEmpty++]=i;
                    while(next[nextEmpty]!=-1)nextEmpty++;
                }
                else next[(int)pos[i]+1] = i;
            }
            if(nextEmpty<k+1)next[nextEmpty] = k+1;
            out.printLine(String.format("Case #%d: %d",cases,next[(int)((n)%(k+1))]));
        }
	}
    }

class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    }

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(outputStream);
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

}

