package com.pablo.sideb.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pablo.sideb.R;
import com.pablo.sideb.model.Cliente;
import com.pablo.sideb.model.Pedido;
import com.pablo.sideb.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class DAO extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String name = "bd_bstore";

    //Tabela que armazena os dados do cliente
    private static final String tb_cliente = "custumer";
    private static final String c_cpf = "cpf";
    private static final String c_nome = "nome";
    private static final String c_email = "email";
    private static final String c_telefone = "telefone";
    //Tabela que armazena os dados do pedido
    private static final String tb_pedido = "orders";
    private static final String c_idPedido = "id";
    private static final String c_dtCompra = "dt_compra";
    private static final String c_itens = "item";
    private static final String c_qtde = "qtde";
    private static final String c_endereco = "endereco";
    private static final String c_valorPedido = "valorcompra";
    //Tabela que armazena os dados dos produtos
    private static final String tb_produtos = "products";
    private static final String c_cod = "cod";
    private static final String c_item = "item";
    private static final String c_descricao = "descricao";
    private static final String c_valor = "valor";
    private static final String c_img = "imagem";
    private static final String c_caracteristicas = "caracteristicas";
    private static final String c_tracklist = "tracklist";

    private String query = "";
    public static Context contexto;

    private ArrayList<Produto> produtos;

    public DAO(@Nullable Context context) {
        super(context, name, null, version);
        contexto = context;
        populaDados();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE cliente (cpf TEXT PRIMARY KEY, ...)
        query = "CREATE TABLE " + tb_cliente + "(" +
                c_cpf + " TEXT PRIMARY KEY NOT NULL, " +
                c_nome + " TEXT NOT NULL, " +
                c_email + " TEXT NOT NULL, " +
                c_telefone + " TEXT NOT NULL)";
        db.execSQL(query);
        query = "";
        //CREATE TABLE orders (id INT, ...)
        query = "CREATE TABLE " + tb_pedido + "(" +
                c_idPedido + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                c_dtCompra + " TEXT NOT NULL, " +
                c_itens + " TEXT NOT NULL, " +
                c_qtde + " INTEGER NOT NULL, " +
                c_endereco + " TEXT NOT NULL, " +
                c_valorPedido + " TEXT NOT NULL)";
        db.execSQL(query);
        query = "";
        //CREATE TABLE products (cod INTEGER PRIMARY KEY, ...)
        query = "CREATE TABLE " + tb_produtos + "(" +
                c_cod + " INTEGER PRIMARY KEY, " +
                c_item + " TEXT, " +
                c_descricao + " TEXT, " +
                c_valor + " TEXT," +
                c_img + " INTEGER," +
                c_caracteristicas + " TEXT, " +
                c_tracklist + " TEXT)";
        db.execSQL(query);

        //INSERT INTO products (cod, item, ...)
        for (Produto prod : produtos) {
            query = "";
            query = "INSERT INTO " + tb_produtos + "(" +
                    c_cod + ", " + c_item + ", " + c_descricao + ", " + c_valor + ", " +
                    c_img + ", " + c_caracteristicas + ", " + c_tracklist + ")" +
                    " VALUES (" + prod.getCod() + ", " + "'" + prod.getItem() + "', '" +
                    prod.getDescricao() + "', '" + prod.getValor() + "', " + prod.getImg() + ", '" +
                    prod.getCaracteristicas() + "', '" + prod.getTracklist() + "')";
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCliente(@NonNull Cliente cli) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO  " + tb_cliente +
                " (" + c_cpf + ", " + c_nome + ", " + c_email + ", " + c_telefone + ") VALUES ('" +
                cli.getCpf() + "', '" + cli.getNome() + "', '" + cli.getEmail() + "', '" + cli.getTelefone() + "')";
        db.execSQL(query);
        db.close();
    }

    public Cliente consultarCliente() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Pedido> pedidoLista = new ArrayList<>();

        String query = "SELECT * FROM " + tb_cliente;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) cursor.moveToFirst();
        else return null;
        Cliente cli = new Cliente();
        cli.setCpf(cursor.getString(0));
        cli.setNome(cursor.getString(1));
        cli.setEmail(cursor.getString(2));
        cli.setTelefone(cursor.getString(3));

        return cli;
    }

    public Pedido consultarPedido(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Pedido ped = new Pedido();

        String query = "SELECT * FROM " + tb_pedido + " WHERE " + c_idPedido + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) cursor.moveToFirst();
        else return null;
        ped.setIdPedido(Integer.parseInt(cursor.getString(0)));
        ped.setDtCompra(cursor.getString(1));
        ped.setItem(cursor.getString(2));
        ped.setQtde(Integer.parseInt(cursor.getString(3)));
        ped.setEnderecoEntrega(cursor.getString(4));
        ped.setValorPedido(cursor.getString(5));

        return ped;
    }

    public void removePedido(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_pedido, c_idPedido + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void addPedido(Pedido ped) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(c_dtCompra, ped.getDtCompra());
        values.put(c_itens, ped.getItem());
        values.put(c_qtde, ped.getQtde());
        values.put(c_endereco, ped.getEnderecoEntrega());
        values.put(c_valorPedido, ped.getValorPedido());
        db.insert(tb_pedido, null, values);
        db.close();
    }

    public void atualizarPedido(int id, String endereco) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + tb_pedido +
                " SET " + c_endereco + " = '" + endereco +
                "' WHERE " + c_idPedido + " = " + id;

        db.execSQL(query);
        db.close();
    }

    public List<Pedido> listarPedidos() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Pedido> pedidoLista = new ArrayList<>();

        String query = "SELECT * FROM " + tb_pedido;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Pedido ped = new Pedido();
                ped.setIdPedido(Integer.parseInt(cursor.getString(0)));
                ped.setDtCompra(cursor.getString(1));
                ped.setItem(cursor.getString(2));
                ped.setQtde(Integer.parseInt(cursor.getString(3)));
                ped.setEnderecoEntrega(cursor.getString(4));
                ped.setValorPedido(cursor.getString(5));
                pedidoLista.add(ped);
            } while (cursor.moveToNext());
        }
        return pedidoLista;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }


    public ArrayList<Produto> listarProdutos() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Produto> prodLista = new ArrayList<>();

        String query = "SELECT * FROM " + tb_produtos;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Produto prod = new Produto();
                prod.setCod(Integer.parseInt(cursor.getString(0)));
                prod.setItem(cursor.getString(1));
                prod.setDescricao(cursor.getString(2));
                prod.setValor(cursor.getString(3));
                prod.setImg(Integer.parseInt(cursor.getString(4)));
                prod.setCaracteristicas(cursor.getString(5));
                prod.setTracklist(cursor.getString(6));

                prodLista.add(prod);
            } while (cursor.moveToNext());
        }
        db.close();
        return prodLista;
    }

    public Produto consultarProduto(int cod){
        SQLiteDatabase db = this.getReadableDatabase();
        Produto prod = new Produto();

        String query = "SELECT * FROM " + tb_produtos + " WHERE " + c_cod + " = " + cod;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) cursor.moveToFirst();
        else return null;
        prod.setCod(Integer.parseInt(cursor.getString(0)));
        prod.setItem(cursor.getString(1));
        prod.setDescricao(cursor.getString(2));
        prod.setValor(cursor.getString(3));
        prod.setImg(Integer.parseInt(cursor.getString(4)));
        prod.setCaracteristicas(cursor.getString(5));
        prod.setTracklist(cursor.getString(6));

        return prod;
    }

    public void populaDados() {
        produtos = new ArrayList<Produto>();

        produtos.add(new Produto(100, "FKA Twigs - Caprisongs",
                "Caprisongs ?? a mixtape de estreia da cantora e compositora inglesa.",
                "R$ 30,99",
                R.drawable.albumcover_caprisongs,
                "Caprisongs ?? a mixtape de estreia da cantora e compositora inglesa FKA Twigs. Foi lan??ado em 14 de janeiro de 2022 pela Young e Atlantic Records. A mixtape conta com participa????es especiais de Pa Salieu, The Weeknd, Shygirl, Dystopia, Rema, Daniel Caesar, Jorja Smith e Unknown T.",
                "1. ride the dragon\n" +
                        "2. honda (feat pa salieu)\n" +
                        "3. meta angel\n" +
                        "4. tears in the club (feat the weeknd)\n" +
                        "5. oh my love\n" +
                        "6. pamplemousse\n" +
                        "7. caprisongs interlude (feat solo)\n" +
                        "8. lightbeamers\n" +
                        "9. papi bones (feat shygirl)\n" +
                        "10. which way (feat dystopia)\n" +
                        "11. jealousy (feat rema)\n" +
                        "12. careless (feat daniel caesar)\n" +
                        "13. minds of men\n" +
                        "14. track girl interlude\n" +
                        "15. darjeeling (feat jorja smith & unknown t)\n" +
                        "16. christi interlude\n" +
                        "17. thank you song"));

        produtos.add(new Produto(101, "The Weeknd - After Hours",
                "After Hours ?? o quarto ??lbum do cantor The Weeknd.",
                "R$ 49,99",
                R.drawable.albumcover_after_hours,
                "After Hours ?? o quarto ??lbum de est??dio do cantor canadense The Weeknd, lan??ado em 20 de mar??o de 2020, atrav??s das gravadoras XO e Republic. Este ?? o primeiro ??lbum de est??dio de The Weeknd desde Starboy, de 2016, que foi seguido pelo EP My Dear Melancholy, em 2018.",
                "1. Alone Again\n" +
                        "2. Too Late\n" +
                        "3. Hardest To Love\n" +
                        "4. Scared To Live\n" +
                        "5. Snowchild\n" +
                        "6. Escape From L.A.\n" +
                        "7. Heartless\n" +
                        "8. Faith\n" +
                        "9. Blinding Lights\n" +
                        "10. In Your Eyes\n" +
                        "11. Save Your Tears\n" +
                        "12. Repeat After Me (Interlude)\n" +
                        "13. After Hours\n" +
                        "14. Until I Bleed Out"));

        produtos.add(new Produto(102, "Paramore - After Hours",
                "After Laughter ?? o quinto ??lbum de est??dio da banda de rock Paramore.",
                "R$ 21,99",
                R.drawable.albumcover_after_laughter,
                "After Laughter ?? o quinto ??lbum de est??dio da banda de rock estadunidense Paramore. Foi lan??ado em 12 de maio de 2017 por interm??dio da Fueled by Ramen sucedendo o Paramore, seu quarto ??lbum de est??dio autointitulado.",
                "1. Hard Times\n" +
                        "2. Rose-Colored Boy\n" +
                        "3. Told You So\n" +
                        "4. Forgiveness\n" +
                        "5. Fake Happy\n" +
                        "6. 26\n" +
                        "7. Pool\n" +
                        "8. Grudges\n" +
                        "9. Caught In The Middle\n" +
                        "10. Idle Worship\n" +
                        "11. No Friend\n" +
                        "12. Tell Me How"));

        produtos.add(new Produto(103, "SZA - Ctrl",
                "??lbum de estreia da cantora e compositora estadunidense SZA.",
                "R$ 32,59",
                R.drawable.albumcover_ctrl,
                "Ctrl ?? o ??lbum de estreia da cantora e compositora estadunidense SZA. Foi lan??ado em 9 de junho de 2017 pelas gravadoras Top Dawg Entertainment e RCA Records.",
                "1. Supermodel\n" +
                        "2. Love Galore ft. Travis Scott\n" +
                        "3. Doves In The Wind ft. Kendrick Lamar\n" +
                        "4. Drew Barrymore\n" +
                        "5. Prom\n" +
                        "6. The Weekend\n" +
                        "7. Go Gina\n" +
                        "8. Garden (Say It Like Dat)\n" +
                        "9. Broken Clocks\n" +
                        "10. Anything\n" +
                        "11. Wavy (Interlude ft. James Fauntleroy\n" +
                        "12. Normal Girl\n" +
                        "13. Pretty Little Birds ft. Isaiah Rashad\n" +
                        "14. 20 Something"));

        produtos.add(new Produto(104, "Tinashe - Songs For You",
                "Quarto ??lbum de est??dio da cantora Tinashe.",
                "R$ 38,99",
                R.drawable.albumcover_songs_for_you,
                "Songs for You ?? o quarto ??lbum de est??dio da cantora estadunidense Tinashe, lan??ado de forma independente pelo seu pr??prio selo, Tinashe Music Inc., em 21 de novembro de 2019.",
                "1. Feelings\n" +
                        "2. Lifes Too Short\n" +
                        "3. Hopscotch\n" +
                        "4. Stormy Weather\n" +
                        "5. Save Room For Us (prod. by MAKJ)\n" +
                        "6. Story Of Us\n" +
                        "7. Die A Little Bit (feat. Ms Banks)\n" +
                        "8. Perfect Crime\n" +
                        "9. Cash Race\n" +
                        "10. Link Up\n" +
                        "11. Touch & Go (feat. 6LACK)\n" +
                        "12. Know Better\n" +
                        "13. You\n" +
                        "14. So Much Better (feat. G-Eazy)\n" +
                        "15. Remember When"));

        produtos.add(new Produto(105, "Lorde - Melodrama",
                "Grammy-nominee, este ?? o segundo ??lbum de est??dio de Lorde.",
                "R$ 49,99",
                R.drawable.albumcover_melodrama,
                "Melodrama ?? o segundo ??lbum de est??dio gravado pela cantora e compositora neozelandesa Lorde. O seu lan??amento ocorreu em 16 de junho de 2017, atrav??s da gravadora Universal Music e suas afiliadas.",
                "1. Green Light\n" +
                        "2. Sober\n" +
                        "3. Homemade Dynamite\n" +
                        "4. The Louvre\n" +
                        "5. Liability\n" +
                        "6. Hard Feelings/Loveless\n" +
                        "7. Sober II (Melodrama)\n" +
                        "8. Writer In the Dark\n" +
                        "9. Supercut\n" +
                        "10. Liability (Reprise)\n" +
                        "11. Perfect Places"));

        produtos.add(new Produto(106, "Lana Del Rey - NFR!",
                "Norman Fucking Rockwell! ?? o sexto ??lbum da cantora americana.",
                "R$ 30,99",
                R.drawable.albumcover_normanf_rockwell,
                "Norman Fucking Rockwell! ?? o sexto ??lbum de est??dio da cantora norte-americana Lana Del Rey. Seu lan??amento ocorreu em 30 de agosto de 2019, por interm??dio da Polydor Records e Interscope Records. O t??tulo do ??lbum foi anunciado em 18 de setembro de 2018, por interm??dio do radialista Zane Lowe, da r??dio Beats 1.",
                "1. Norman Fucking Rockwell\n" +
                        "2. Mariners Apartment Complex\n" +
                        "3. Venice Bitch\n" +
                        "4. Fuck It I Love You\n" +
                        "5. Doin??? Time\n" +
                        "6. Love Song\n" +
                        "7. Cinnamon Girl\n" +
                        "8. How to Disappear\n" +
                        "9. California\n" +
                        "10. The Next Best American Record\n" +
                        "11. The Greatest\n" +
                        "12. Bartender\n" +
                        "13. Happiness Is a Butterfly\n" +
                        "14. Hope Is a Dangerous Thing for Woman Like Me to Have ??? but I Have It"));

        produtos.add(new Produto(107, "The Weeknd - Dawn FM",
                "Dawn FM ?? o quinto ??lbum do cantor e compositor The Weeknd.",
                "R$ 78,99",
                R.drawable.albumcover_dawnfm,
                "Dawn FM ?? o quinto ??lbum de est??dio do cantor e compositor canadense The Weeknd, lan??ado em 7 de janeiro de 2022, atrav??s das gravadoras XO e Republic Records.",
                "1. Dawn FM\n" +
                        "2. Gasoline\n" +
                        "3. How do I make you love me?\n" +
                        "4. Take my breath\n" +
                        "5. Sacrifice\n" +
                        "6. A tale by Quincy\n" +
                        "7. Out of time\n" +
                        "8. Here we go... again\n" +
                        "9. Best friends\n" +
                        "10. Is there someone else?\n" +
                        "11. Starry Eyes\n" +
                        "12. Every angel is terrifying\n" +
                        "13. Don t break my heart\n" +
                        "14. I heard you re married\n" +
                        "15. Less than zero\n" +
                        "16. Phantom regret by Jim"));

        produtos.add(new Produto(108, "Rosal??a - MOTOMAMI",
                "MOTOMAMI ?? o sucessor do aclamado El Mal Querer.",
                "R$ 54,99",
                R.drawable.albumcover_motomami,
                "Motomami ?? o terceiro ??lbum de est??dio da cantora e compositora espanhola Rosal??a, lan??ado em 18 de mar??o de 2022 pela Columbia Records.",
                "1. Saoko\n" +
                        "2. Candy\n" +
                        "3. La Fama (feat. The Weeknd)\n" +
                        "4. Buler??as\n" +
                        "5. Chicken Teriyaki\n" +
                        "6. Hentai\n" +
                        "7. Bizcochito\n" +
                        "8. G3N15\n" +
                        "9. MOTOMAMI\n" +
                        "10. Diablo\n" +
                        "11. Delirio de Grandeza\n" +
                        "12. Cuuuuuuuuuute\n" +
                        "13. Como un G\n" +
                        "14. MOTOMAMI Alphabet\n" +
                        "15. La Combi Versace (feat. Tokischa)\n" +
                        "16. Sakura"));


        produtos.add(new Produto(109, "Rina Sawayama - Hold The Girl",
                "Segundo ??lbum da cantora traz inova????es.",
                "R$ 32,87",
                R.drawable.albumcover_holdthegirl,
                "Hold the Girl ?? o segundo ??lbum de est??dio da cantora e compositora nipo-brit??nica Rina Sawayama, lan??ado em 16 de setembro de 2022, atrav??s da Dirty Hit. O primeiro single do ??lbum, intitulado \"This Hell\", foi lan??ado em 18 de maio de 2022.",
                "1. Minor Feelings\n" +
                        "2. Hold The Girl\n" +
                        "3. This Hell\n" +
                        "4. Catch Me In The Air\n" +
                        "5. Forgiveness\n" +
                        "6. Holy (Till You Let Me Go)\n" +
                        "7. Your Age\n" +
                        "8. Imagining\n" +
                        "9. Frankenstein\n" +
                        "10. Hurricanes\n" +
                        "11. Send My Love To John\n" +
                        "12. Phantom\n" +
                        "13. To Be Alive"));

        produtos.add(new Produto(110, "Calvin Harris - Funk Wav Bounces Vol.2",
                "Sexto ??lbum do DJ traz a vibe do ver??o.",
                "R$ 54,99",
                R.drawable.albumcover_funkwavbounces,
                "Funk Wav Bounces Vol. 2 ?? o sexto ??lbum de est??dio do DJ e produtor musical escoc??s Calvin Harris. Foi lan??ado em 5 de agosto de 2022 pela Columbia Records.",
                "1. Intro\n" +
                        "2. New Money (feat. 21 Savage)\n" +
                        "3. Potion (With Dua Lipa & Young Thug)\n" +
                        "4. Woman Of The Year (Feat. Stefflon Don, Chloe Bailey & Coi Leray)\n" +
                        "5. Obsessed (Feat. Charlie Puth & Shenseea)\n" +
                        "6. New To You (Feat. Normani, Tinashe & Offset)\n" +
                        "7. Ready Or Not (Feat. Busta Rhymes)\n" +
                        "8. Stay With Me (feat. Pharrell, Justin Timberlake & Halsey)\n" +
                        "9. Stay With Me (Part. 2) (Feat. Justin Timberlake, Halsey & Pharrell)\n" +
                        "10. Somebody Else (Feat. Jorja Smith & Lil Durk)\n" +
                        "11. Nothing More To Say (Feat. 6Lack & Donae o)\n" +
                        "12. Live My Best Life (Feat. Snoop Dogg & Latto)\n" +
                        "13. Lean On Me (Feat. Swae Lee)\n" +
                        "14. Day One (Feat. Pharrell & Pusha T)"));
    }

}
