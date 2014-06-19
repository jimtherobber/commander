package de.hensel.e4.test.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.hensel.e4.test.parts.TestPart;

public class FileReaderIO {
	
	private String testFilePath;
	
	public File openFileDialog(Shell shell){
		FileDialog dialog = new FileDialog(shell);
		dialog.open();
		File file = new File(dialog.getFilterPath()+ "\\" + dialog.getFileName());
		return file;
	}

	public String readFile(File file){
		
		StringBuffer sb = new StringBuffer();
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String zeile = "";
			
			while( (zeile = br.readLine()) != null )
			{
				sb.append(zeile);
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public void writeFile(String text){
		String fileSuffix = String.valueOf(new Date().getTime());
		setTestFilePath("C:\\temp\\test"+fileSuffix+".txt");
		PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("C:\\temp\\test"+fileSuffix+".txt")));
            pWriter.print(text);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null){
                pWriter.flush();
                pWriter.close();
            }
        } 
	}

	public String getTestFilePath() {
		return testFilePath;
	}

	public void setTestFilePath(String testFilePath) {
		this.testFilePath = testFilePath;
	}
}
