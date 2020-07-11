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

import org.w3c.dom.Text;

import java.util.ArrayList;

//adapter의 기본 구색
public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.CustomViewHolder> {

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;
    private ArrayList<User> arrayList; //아까 만든 class에서의 User
    private Context context;

    public CustomAdapter3(ArrayList<User> arrayList, Context context,
                          OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item3, parent, false);
        CustomViewHolder holder3 = new CustomViewHolder(view);
        return holder3;
    }//adapter에 연결이 되고난 후 viewholder를 최초로 만들어 낸다.


    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter3.CustomViewHolder holder, final int position) {


        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference imageRef = storageReference.child("ManagerImages")
                .child(arrayList.get(position).uid+".png");
        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(holder.itemView)
                        .load(uri)
                        .apply(new RequestOptions().circleCrop())
                        .into(holder.iv_profile2);
            }
        });
        holder.userName.setText(arrayList.get(position).userName);
        holder.userAddress.setText(arrayList.get(position).address);
        holder.userJob.setText("직업: " + arrayList.get(position).job);
        holder.userAge.setText("나이: " + arrayList.get(position).age);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        //삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder { //listview에서 만든 것들을 여기로 불러 놓을 거임
        ImageView iv_profile2;
        TextView userName;
        TextView userAddress;
        TextView userAge;
        TextView userJob;
        Button button;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView); //view 에서 상속을 받았기 때문에 itemView에서 찾아야한다
            this.iv_profile2 = itemView.findViewById(R.id.iv_profile2);
            this.userName = itemView.findViewById(R.id.list_managerName);
            this.userAddress = itemView.findViewById(R.id.list_managerAddress);
            this.userAge = itemView.findViewById(R.id.list_managerAge);
            this.userJob = itemView.findViewById(R.id.list_managerJob);
            this.button = itemView.findViewById(R.id.button2);
        }
    }
}
