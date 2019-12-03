package ar.edu.itba.inge.pab.ui;

import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import ar.edu.itba.inge.pab.MyApplication;
import ar.edu.itba.inge.pab.R;
import ar.edu.itba.inge.pab.elements.Project;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    private List<Project> data;
    private OnItemClickListener<Project> listener;

    public ProjectAdapter(List<Project> data, OnItemClickListener<Project> itemClickListener) {
        this.data = data;
        this.listener = itemClickListener;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProjectViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.bind(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView tvProjectName;
        TextView tvProjectText;
        LinearLayout tvProjectExp;
        ImageButton arrowButton;

        public ProjectViewHolder (View itemView) {
            super(itemView);
            tvProjectName = itemView.findViewById(R.id.tv_project_name);
            tvProjectText = itemView.findViewById(R.id.tv_project_text);
            tvProjectExp = itemView.findViewById(R.id.tv_project_exp);
            arrowButton = itemView.findViewById(R.id.arrow_down);
        }

        public void bind(final Project project, final OnItemClickListener<Project> listener) {
            tvProjectName.setText(project.getTitulo());
            tvProjectText.setText(project.getDescripcion());

            itemView.setOnClickListener(view -> listener.onItemClick(project));
            arrowButton.setOnClickListener(view -> {
                TransitionManager.beginDelayedTransition((ViewGroup) view.getRootView());
                if (tvProjectExp.getVisibility() != View.VISIBLE) {
                    tvProjectExp.setVisibility(View.VISIBLE);
                    arrowButton.setImageResource(R.drawable.ic_arrow_up_black_24dp);
                } else {
                    tvProjectExp.setVisibility(View.GONE);
                    arrowButton.setImageResource(R.drawable.ic_arrow_down_black_24dp);
                }
            });
        }
    }
}
