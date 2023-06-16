package br.ufscar.dc.compiladores.alguma.sintatico;

import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.nio.charset.StandardCharsets;
import java.io.FileWriter;
import java.io.IOException;

public class Principal {
    public static void main(String args[]) throws IOException {

        try {
            CharStream cs = CharStreams.fromFileName(args[0]);
            AlgumaLexer lexer = new AlgumaLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AlgumaParser parser = new AlgumaParser(tokens);

            String filename = args[1];
            FileWriter writer = new FileWriter(filename);
            
            MyCustomErrorListener mcel = new MyCustomErrorListener(writer);
            parser.addErrorListener(mcel);
            parser.removeErrorListeners();

            parser.programa();

            // Fechamento do arquivo de saída
            writer.write("teste");
            writer.close();
        }catch (IOException ex) {
        }
    }
}
