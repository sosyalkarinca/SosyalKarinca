package sosyalkarinca;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.kardelen.sosyalkarinca.R;

public class LoginActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		Button button = (Button) findViewById(R.id.loginButon);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		EditText userText = (EditText) findViewById(R.id.usernameText);
		EditText passText = (EditText) findViewById(R.id.passText);
		
		// burada login ol
		if(userText.equals("admin") && passText.equals("admin")) {
			
		}
		
	}

}
