    package com.example.passwordmanager.SQLiteDatabase;

    import android.app.AlertDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.example.passwordmanager.Accueil;
    import com.example.passwordmanager.R;
    import com.example.passwordmanager.SQLiteDatabase.Updates.CardUpdate;
    import com.example.passwordmanager.SQLiteDatabase.Updates.LoginUpdate;
    import com.example.passwordmanager.SQLiteDatabase.Updates.NoteUpdate;

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

                    ImageView update_login = convertView.findViewById(R.id.update_Login);
                    ImageView delete_Login = convertView.findViewById(R.id.delete_Login);


   //////////////////////////////// To save data and display it in Update page ////////////////////////////////
                    update_login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, LoginUpdate.class);
                            intent.putExtra("title",login.getTitle());
                            intent.putExtra("email",login.getEmail());
                            intent.putExtra("password",login.getPassword());
                            intent.putExtra("id",login.getId());

                            mContext.startActivity(intent);
                        }
                    });


                    // TO DELETE FROM DATABASE
                    delete_Login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setTitle("Confirm Delete");
                            builder.setMessage("Are you sure you want to delete this login?");

                            // Add the buttons
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User clicked OK button
                                    PASMAN_Database db = new PASMAN_Database(mContext);
                                    db.deleteLogin(String.valueOf(login.getId()));
                                    Intent intent = new Intent(mContext, Accueil.class);
                                    mContext.startActivity(intent);
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                    dialog.dismiss();
                                }
                            });

                            // Create the AlertDialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

                    break;

                case 1: // Credit card item
                    TextView titleTv = convertView.findViewById(R.id.itemCard_titleTv);
                    TextView cardNumberTextView = convertView.findViewById(R.id.cardNumberTv);
                    TextView typeTv = convertView.findViewById(R.id.typeTv);
                    TextView cardHolder = convertView.findViewById(R.id.cardHolderTv);
                    TextView monthTv = convertView.findViewById(R.id.mTv);
                    TextView yearTv = convertView.findViewById(R.id.yTv);
                    TextView cvvTextView = convertView.findViewById(R.id.cvcTv);
                    TextView pin = convertView.findViewById(R.id.pinTv);

                    CreditCard creditCard = (CreditCard) item;
                    titleTv.setText(creditCard.getTitle());
                    cardNumberTextView.setText(String.valueOf(creditCard.getCardNumber())); // convert int to String
                    typeTv.setText(creditCard.getType());
                    cardHolder.setText(creditCard.getCardHolder());
                    monthTv.setText(String.valueOf(creditCard.getMonth()));
                    yearTv.setText(String.valueOf(creditCard.getYear()));
                    cvvTextView.setText(String.valueOf(creditCard.getCvc()));
                    pin.setText(String.valueOf(creditCard.getPin()));

                    // To save data and display it in Update credit card
                    ImageView update_card = convertView.findViewById(R.id.update_CreditCard);
                    ImageView delete_card = convertView.findViewById(R.id.delete_CreditCard);
                    update_card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, CardUpdate.class);
                            intent.putExtra("title",creditCard.getTitle());
                            intent.putExtra("cardNumber",creditCard.getCardNumber()); // int can be directly put as extra
                            intent.putExtra("type",creditCard.getType());
                            intent.putExtra("cardHolder",creditCard.getCardHolder());
                            intent.putExtra("month",creditCard.getMonth());
                            intent.putExtra("year",creditCard.getYear());
                            intent.putExtra("cvc",creditCard.getCvc());
                            intent.putExtra("pin",creditCard.getPin());
                            intent.putExtra("id", creditCard.getId());
                            mContext.startActivity(intent);
                        }
                    });

                    // To delete from database
                    delete_card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setTitle("Confirm Delete");
                            builder.setMessage("Are you sure you want to delete this credit card?");

                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User clicked OK button
                                    PASMAN_Database db = new PASMAN_Database(mContext);
                                    db.deleteCreditCard(String.valueOf(creditCard.getId()));
                                    Intent intent = new Intent(mContext, Accueil.class);
                                    mContext.startActivity(intent);
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

                    break;

                case 2: // Note item
                    TextView noteTitleTextView = convertView.findViewById(R.id.itemNote_titleTv);
                    TextView description = convertView.findViewById(R.id.descriptionTv);

                    Note note = (Note) item;
                    noteTitleTextView.setText(note.getTitle());
                    description.setText(note.getDescription());

    /////////////////////// To save data and display in Update Note /////////////////////////////////////////
                    ImageView update_note = convertView.findViewById(R.id.update_Note);
                    ImageView delete_note = convertView.findViewById(R.id.delete_note);
                    update_note.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, NoteUpdate.class);
                            intent.putExtra("title",note.getTitle());
                            intent.putExtra("description",note.getDescription());

                            intent.putExtra("id",note.getId());
                            mContext.startActivity(intent);
                        }
                    });

                    // TO DELETE FROM DATABASE
                    delete_note.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setTitle("Confirm Delete");
                            builder.setMessage("Are you sure you want to delete this login?");

                            // Add the buttons
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User clicked OK button
                                    PASMAN_Database db = new PASMAN_Database(mContext);
                                    db.deleteNote(String.valueOf(note.getId()));
                                    Intent intent = new Intent(mContext, Accueil.class);
                                    mContext.startActivity(intent);
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                    dialog.dismiss();
                                }
                            });

                            // Create the AlertDialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });



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

