package com.techdecode.pizzaapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class useFoodListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Food> foodList;


    public useFoodListAdapter(Context context, int layout, ArrayList<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }



    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder{
        ImageView imageView;
        TextView txtName,txtPrice,txtType;
        Button btn;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.txtName = row.findViewById(R.id.user_food_name);
            holder.imageView = row.findViewById(R.id.use_food_image);
            holder.txtPrice = row.findViewById(R.id.user_food_price);
            holder.txtType = row.findViewById(R.id.user_food_type);


            row.setTag(holder);

        }else{
            holder = (useFoodListAdapter.ViewHolder) row.getTag();
        }

        Food food = foodList.get(position);

        holder.txtName.setText(food.getName());
        holder.txtPrice.setText(food.getPrice());
        holder.txtType.setText(food.getType());

        byte[] foodImage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, user_inside_food_item.class);
                intent.putExtra("name1",food.getName());
                intent.putExtra("price1",food.getPrice());
                intent.putExtra("type1",food.getType());
                intent.putExtra("image1",food.getImage());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            }
        });



        return row;
    }
}
