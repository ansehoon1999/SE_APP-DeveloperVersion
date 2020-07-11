package org.techtown.se_project_manager;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

//adapter의 기본 구색
public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.CustomViewHolder> {
    private ArrayList<ReservationModel> arrayList; //아까 만든 class에서의 User
    private Context context;
    public CustomAdapter2(ArrayList<ReservationModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item2, parent, false);
        CustomViewHolder holder2 = new CustomViewHolder(view);
        return holder2;
    }//adapter에 연결이 되고난 후 viewholder를 최초로 만들어 낸다.


    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter2.CustomViewHolder holder, final int position) {


        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference imageRef = storageReference.child("ClientImages").child(arrayList.get(position).client+".png");
        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.itemView)
                        .load(uri)
                        .apply(new RequestOptions().circleCrop())
                        .into(holder.reservation_client_profile);
            }
        });
         imageRef = storageReference.child("ManagerImages").child(arrayList.get(position).manager+".png");
        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.itemView)
                        .load(uri)
                        .apply(new RequestOptions().circleCrop())
                        .into(holder.reservation_manager_profile);
            }
        });

        holder.reservation_date.setText(arrayList.get(position).date);


    }

    @Override
    public int getItemCount() {
        //삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder { //listview에서 만든 것들을 여기로 불러 놓을 거임
        ImageView reservation_client_profile;
        ImageView reservation_manager_profile;
        TextView reservation_date;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView); //view 에서 상속을 받았기 때문에 itemView에서 찾아야한다
            this.reservation_client_profile = itemView.findViewById(R.id.reservation_client_profile);
            this.reservation_manager_profile = itemView.findViewById(R.id.reservation_manager_profile);
            this.reservation_date = itemView.findViewById(R.id.reservation_date);
        }
    }
}
