package nta.com.music.myfpl.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.model.SlideItem;

    public class SlideImage_Adapter extends RecyclerView.Adapter<SlideImage_Adapter.SildeViewHolder> {

    private List<SlideItem> slideItems;
    private ViewPager2 viewPager2;

        public SlideImage_Adapter(List<SlideItem> slideItems, ViewPager2 viewPager2) {
            this.slideItems = slideItems;
            this.viewPager2 = viewPager2;
        }

        @NonNull
        @Override
        public SildeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SildeViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_container, parent, false)

            );
        }

        @Override
        public void onBindViewHolder(@NonNull SildeViewHolder holder, int position) {
            holder.setImage(slideItems.get(position));
            if (position ==slideItems.size()-2){
                viewPager2.post(runnable);

            }
        }

        @Override
        public int getItemCount() {
            return slideItems.size();
        }

        static class  SildeViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;

         SildeViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageSlide);
        }
        void setImage(SlideItem slideItem) {

            //if you want to display image from internet you can code here gile
            imageView.setImageResource(slideItem.getImage());
        }

    }

    private Runnable runnable = new Runnable() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {
            slideItems.addAll(slideItems);
            notifyDataSetChanged();
        }
    };
}
