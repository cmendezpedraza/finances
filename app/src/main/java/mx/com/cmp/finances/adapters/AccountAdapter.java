package mx.com.cmp.finances.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import mx.com.cmp.finances.R;
import mx.com.cmp.finances.objects.Account;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.CardViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Account> accountList;

    public AccountAdapter(Context context, List<Account> accountList) {
        this.context = context;
        this.accountList = accountList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accounts, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.accountName.setText(account.getAccountName());
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.accountIcon.setColorFilter(color);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        public TextView accountName;
        public ImageView accountIcon;

        public CardViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_accounts);
            accountName = (TextView) itemView.findViewById(R.id.item_account_name);
            accountIcon = (ImageView) itemView.findViewById(R.id.item_account_icon);
        }
    }
}
