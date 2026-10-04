package com.mcchat.app;

import android.app.*;import android.os.*;import android.graphics.Color;import android.graphics.Typeface;import android.graphics.drawable.GradientDrawable;import android.view.*;import android.widget.*;import java.util.*;

public class MainActivity extends Activity {
    LinearLayout root, list; int green=Color.rgb(7,94,84), light=Color.rgb(242,247,245);
    TextView tv(String s,int sp){ TextView t=new TextView(this); t.setText(s); t.setTextSize(sp); t.setTextColor(Color.DKGRAY); t.setPadding(18,10,18,10); return t; }
    GradientDrawable bg(int c,float r){GradientDrawable g=new GradientDrawable();g.setColor(c);g.setCornerRadius(r);return g;}
    @Override public void onCreate(Bundle b){super.onCreate(b);showHome();}
    void showHome(){
        root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(Color.WHITE);
        LinearLayout bar=new LinearLayout(this);bar.setGravity(Gravity.CENTER_VERTICAL);bar.setPadding(12,8,8,8);bar.setBackgroundColor(green);
        TextView title=tv("McChat",22);title.setTextColor(Color.WHITE);title.setTypeface(null,Typeface.BOLD);bar.addView(title,new LinearLayout.LayoutParams(0,64,1));
        TextView search=tv("⌕",30);search.setTextColor(Color.WHITE);bar.addView(search,new LinearLayout.LayoutParams(55,64));
        TextView menu=tv("⋮",28);menu.setTextColor(Color.WHITE);bar.addView(menu,new LinearLayout.LayoutParams(45,64));root.addView(bar);
        LinearLayout tabs=new LinearLayout(this);tabs.setBackgroundColor(green);String[] ts={"CHATS","STATUS","CALLS"};for(String x:ts){TextView t=tv(x,13);t.setTextColor(Color.WHITE);t.setGravity(17);tabs.addView(t,new LinearLayout.LayoutParams(0,48,1));}root.addView(tabs);
        list=new LinearLayout(this);list.setOrientation(LinearLayout.VERTICAL);root.addView(list,new LinearLayout.LayoutParams(-1,0,1));
        addChat("Bright","Hey, how are you?","10:24 AM",true);addChat("Family Group","Mom: 🙏","9:45 AM",true);addChat("Blessing","Thanks bro!","8:32 AM",false);addChat("Zed Music","New song available 🔥","Yesterday",false);addChat("Chisomo","See you later","Yesterday",false);addChat("Work Team","Meeting at 2pm","Yesterday",false);addChat("John","Okay","Sunday",false);
        Button newChat=new Button(this);newChat.setText("＋  New Chat");newChat.setTextColor(Color.WHITE);newChat.setBackground(bg(Color.rgb(0,137,91),60));newChat.setOnClickListener(v->showNewChat());LinearLayout.LayoutParams np=new LinearLayout.LayoutParams(-2,60);np.gravity=Gravity.RIGHT;np.setMargins(0,0,18,12);root.addView(newChat,np);
        setContentView(root);
    }
    void addChat(String name,String msg,String time,boolean unread){LinearLayout row=new LinearLayout(this);row.setGravity(Gravity.CENTER_VERTICAL);row.setPadding(12,8,12,8);TextView av=tv("●",30);av.setTextColor(Color.rgb(0,150,100));row.addView(av,new LinearLayout.LayoutParams(55,70));LinearLayout c=new LinearLayout(this);c.setOrientation(LinearLayout.VERTICAL);TextView n=tv(name,17);n.setTypeface(null,Typeface.BOLD);c.addView(n);c.addView(tv(msg,14));row.addView(c,new LinearLayout.LayoutParams(0,70,1));TextView tm=tv(time,12);tm.setGravity(Gravity.TOP|Gravity.RIGHT);if(unread)tm.setText(time+"\n  ●");row.addView(tm,new LinearLayout.LayoutParams(85,70));row.setOnClickListener(v->showChat(name));list.addView(row);View line=new View(this);line.setBackgroundColor(0xFFE7E7E7);list.addView(line,new LinearLayout.LayoutParams(-1,1));}
    void showChat(String name){
        root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(0xFFF0F5F2);
        LinearLayout bar=new LinearLayout(this);bar.setGravity(Gravity.CENTER_VERTICAL);bar.setBackgroundColor(green);TextView back=tv("‹",38);back.setTextColor(Color.WHITE);back.setOnClickListener(v->showHome());bar.addView(back,new LinearLayout.LayoutParams(55,70));TextView n=tv(name+"\nonline",17);n.setTextColor(Color.WHITE);n.setTypeface(null,Typeface.BOLD);bar.addView(n,new LinearLayout.LayoutParams(0,70,1));TextView call=tv("☎  ◉  ⋮",24);call.setTextColor(Color.WHITE);bar.addView(call,new LinearLayout.LayoutParams(120,70));root.addView(bar);
        LinearLayout messages=new LinearLayout(this);messages.setOrientation(LinearLayout.VERTICAL);messages.setPadding(10,20,10,10);TextView today=tv("Today",12);today.setGravity(17);messages.addView(today);bubble(messages,"Hey, how are you?",true);bubble(messages,"I'm good bro. Wbu?",false);bubble(messages,"I'm fine too. Just working on something important.",true);bubble(messages,"Nice! Keep going 👍",false);bubble(messages,"Thanks bro!",true);root.addView(messages,new LinearLayout.LayoutParams(-1,0,1));
        LinearLayout input=new LinearLayout(this);input.setPadding(8,8,8,8);EditText e=new EditText(this);e.setHint("Type a message");e.setSingleLine();e.setBackground(bg(Color.WHITE,45));input.addView(e,new LinearLayout.LayoutParams(0,58,1));Button send=new Button(this);send.setText("➤");send.setTextColor(Color.WHITE);send.setBackground(bg(Color.rgb(0,137,91),60));send.setOnClickListener(v->{String s=e.getText().toString().trim();if(!s.isEmpty()){bubble(messages,s,true);e.setText("");}});input.addView(send,new LinearLayout.LayoutParams(58,58));root.addView(input);setContentView(root);
    }
    void bubble(LinearLayout m,String s,boolean mine){TextView b=tv(s,15);b.setTextColor(Color.DKGRAY);b.setBackground(bg(mine?0xFFD9FDD3:Color.WHITE,24));LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-2,-2);p.gravity=mine?Gravity.RIGHT:Gravity.LEFT;p.setMargins(30,5,30,5);m.addView(b,p);}
    void showNewChat(){showChat("New Contact");}
}
