package com.pablo.sideb.dao;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.pablo.sideb.R;
import com.pablo.sideb.model.Produto;

import java.util.ArrayList;


public class DAOProduto extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String name = "bd_bstore";

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


    public DAOProduto(@Nullable Context context) {
        super(context, name, null, version);
        contexto = context;
        populaDados();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE cliente (cpf TEXT PRIMARY KEY, ...)
        query = "CREATE TABLE " + tb_produtos + "(" +
                c_cod + " INTEGER PRIMARY KEY, " +
                c_item + " TEXT, " +
                c_descricao + " TEXT, " +
                c_valor + " TEXT," +
                c_img + " INTEGER," +
                c_caracteristicas + " TEXT, " +
                c_tracklist + " TEXT)";
        db.execSQL(query);

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

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public void addProduto() {

        Produto prod = new Produto(1000, "FKA Twigs - Caprisongs",
                "Insira aqui sua breve descrição sobre a obra ou produto.",
                "R$ 30,99", R.drawable.albumcover_caprisongs, "N/A", "N/A");


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
        return prodLista;
    }

    public void populaDados() {
        produtos = new ArrayList<Produto>();

        produtos.add(new Produto(100, "FKA Twigs - Caprisongs",
                "Caprisongs é a mixtape de estreia da cantora e compositora inglesa.",
                "R$ 30,99",
                R.drawable.albumcover_caprisongs,
                "Caprisongs é a mixtape de estreia da cantora e compositora inglesa FKA Twigs. Foi lançado em 14 de janeiro de 2022 pela Young e Atlantic Records. A mixtape conta com participações especiais de Pa Salieu, The Weeknd, Shygirl, Dystopia, Rema, Daniel Caesar, Jorja Smith e Unknown T.",
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
                "After Hours é o quarto álbum do cantor The Weeknd.",
                "R$ 49,99",
                R.drawable.albumcover_after_hours,
                "After Hours é o quarto álbum de estúdio do cantor canadense The Weeknd, lançado em 20 de março de 2020, através das gravadoras XO e Republic. Este é o primeiro álbum de estúdio de The Weeknd desde Starboy, de 2016, que foi seguido pelo EP My Dear Melancholy, em 2018.",
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
                "After Laughter é o quinto álbum de estúdio da banda de rock Paramore.",
                "R$ 21,99",
                R.drawable.albumcover_after_laughter,
                "After Laughter é o quinto álbum de estúdio da banda de rock estadunidense Paramore. Foi lançado em 12 de maio de 2017 por intermédio da Fueled by Ramen sucedendo o Paramore, seu quarto álbum de estúdio autointitulado.",
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
                "Álbum de estreia da cantora e compositora estadunidense SZA.",
                "R$ 32,59",
                R.drawable.albumcover_ctrl,
                "Ctrl é o álbum de estreia da cantora e compositora estadunidense SZA. Foi lançado em 9 de junho de 2017 pelas gravadoras Top Dawg Entertainment e RCA Records.",
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
                "Quarto álbum de estúdio da cantora Tinashe.",
                "R$ 38,99",
                R.drawable.albumcover_songs_for_you,
                "Songs for You é o quarto álbum de estúdio da cantora estadunidense Tinashe, lançado de forma independente pelo seu próprio selo, Tinashe Music Inc., em 21 de novembro de 2019.",
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
                "Grammy-nominee, este é o segundo álbum de estúdio de Lorde.",
                "R$ 49,99",
                R.drawable.albumcover_melodrama,
                "Melodrama é o segundo álbum de estúdio gravado pela cantora e compositora neozelandesa Lorde. O seu lançamento ocorreu em 16 de junho de 2017, através da gravadora Universal Music e suas afiliadas.",
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
                "Norman Fucking Rockwell! é o sexto álbum da cantora americana.",
                "R$ 30,99",
                R.drawable.albumcover_normanf_rockwell,
                "Norman Fucking Rockwell! é o sexto álbum de estúdio da cantora norte-americana Lana Del Rey. Seu lançamento ocorreu em 30 de agosto de 2019, por intermédio da Polydor Records e Interscope Records. O título do álbum foi anunciado em 18 de setembro de 2018, por intermédio do radialista Zane Lowe, da rádio Beats 1.",
                "1. Norman Fucking Rockwell\n" +
                        "2. Mariners Apartment Complex\n" +
                        "3. Venice Bitch\n" +
                        "4. Fuck It I Love You\n" +
                        "5. Doin’ Time\n" +
                        "6. Love Song\n" +
                        "7. Cinnamon Girl\n" +
                        "8. How to Disappear\n" +
                        "9. California\n" +
                        "10. The Next Best American Record\n" +
                        "11. The Greatest\n" +
                        "12. Bartender\n" +
                        "13. Happiness Is a Butterfly\n" +
                        "14. Hope Is a Dangerous Thing for Woman Like Me to Have — but I Have It"));
    }


}
