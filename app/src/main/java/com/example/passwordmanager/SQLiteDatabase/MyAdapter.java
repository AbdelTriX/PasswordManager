    package com.example.passwordmanager.SQLiteDatabase;

    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.example.passwordmanager.R;
    import com.example.passwordmanager.SQLiteDatabase.Login;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;

    public class MyAdapter extends BaseAdapter {
        private Context mContext;
        private List<Main> mDataList;

        public MyAdapter(Context context, List<Main> dataList) {
            mContext = context;
            mDataList = dataList;
        }

        @Override
        public int getCount() {
            return mDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Object item = mDataList.get(position);
            int viewType = getItemViewType(position);

            if (convertView == null) {
                switch (viewType) {
                    case 0: // Login item
                        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_login, parent, false);
                        break;
                    case 1: // Credit card item
                        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_credit_card, parent, false);
                        break;
                    case 2: // Note item
                        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_note, parent, false);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid view type");
                }
            }

            switch (viewType) {
                case 0: // Login item
                    TextView titleTextView = convertView.findViewById(R.id.itemLogin_titleTv);
                    TextView usernameTextView = convertView.findViewById(R.id.emailTv);
                    TextView passwordTextView = convertView.findViewById(R.id.passwordTv);

                    Login login = (Login) item;
                    titleTextView.setText(login.getTitle());
                    usernameTextView.setText(login.getEmail());
                    passwordTextView.setText(login.getPassword());
                    break;
                case 1: // Credit card item
                    TextView titleTv = convertView.findViewById(R.id.itemCard_titleTv);
                    TextView cardNumberTextView = convertView.findViewById(R.id.cardNumberTv);
                    TextView typeTv = convertView.findViewById(R.id.typeTv);
                    TextView cardHolder = convertView.findViewById(R.id.cardHolderTv);
                    TextView expirationTextView = convertView.findViewById(R.id.expiryTv);
                    TextView cvvTextView = convertView.findViewById(R.id.cvcTv);
                    TextView pin = convertView.findViewById(R.id.pinTv);

                    CreditCard creditCard = (CreditCard) item;
                    titleTv.setText(creditCard.getTitle());
                    cardNumberTextView.setText(String.valueOf(creditCard.getCardNumber()));
                    typeTv.setText(creditCard.getType());
                    cardHolder.setText(creditCard.getCardHolder());
                    expirationTextView.setText(creditCard.getExpiry());
                    cvvTextView.setText(String.valueOf(creditCard.getCvc()));
                    pin.setText(String.valueOf(creditCard.getPin()));
                    break;
                case 2: // Note item
                    TextView noteTitleTextView = convertView.findViewById(R.id.itemNote_titleTv);
                    TextView description = convertView.findViewById(R.id.descriptionTv);

                    Note note = (Note) item;
                    noteTitleTextView.setText(note.getTitle());
                    description.setText(note.getDescription());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid view type");
            }

            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            Main main = mDataList.get(position);

            if (main instanceof Login) {
                return 0;
            } else if (main instanceof CreditCard) {
                return 1;
            } else if (main instanceof Note) {
                return 2;
            } else {
                throw new IllegalArgumentException("Invalid item type");
            }
        }

        @Override
        public int getViewTypeCount() {
            return 3; // Number of different item types
        }
    }

