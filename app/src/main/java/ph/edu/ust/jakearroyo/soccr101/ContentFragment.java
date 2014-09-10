package ph.edu.ust.jakearroyo.soccr101;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.achep.header2actionbar.HeaderFragment;

public class ContentFragment extends HeaderFragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setHeaderBackgroundScrollMode(HEADER_BACKGROUND_SCROLL_PARALLAX);
        setOnHeaderScrollChangedListener(new OnHeaderScrollChangedListener() {
            @Override
            public void onHeaderScrollChanged(float progress, int height, int scroll) {
                height -= getActivity().getActionBar().getHeight();

                progress = (float) scroll / height;
                if (progress > 1f) progress = 1f;

                progress = (1 - (float) Math.cos(progress * Math.PI)) * 0.5f;

                ((About) getActivity())
                        .getFadingActionBarHelper()
                        .setActionBarAlpha((int) (255 * progress));
            }
        });
    }

    @Override
    public View onCreateHeaderView(LayoutInflater inflater, ViewGroup container) {
        FrameLayout header = (FrameLayout)inflater.inflate(R.layout.fragment_header, container, false);
        TextView name = (TextView)header.findViewById(R.id.title);
        name.setText(((About)getActivity()).getOrg().getName());
        return header;
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
        RelativeLayout content = (RelativeLayout) inflater.inflate(R.layout.fragment_content, container, false);
        TextView overview = (TextView)content.findViewById(R.id.org_overview);
        overview.setText(Html.fromHtml(((About) getActivity()).getOrg().getDescription()));
        return content;
    }

    @Override
    public View onCreateContentOverlayView(LayoutInflater inflater, ViewGroup container) {
        ProgressBar progressBar = new ProgressBar(getActivity());
        FrameLayout overlay = new FrameLayout(getActivity());
        return overlay;
    }
}
