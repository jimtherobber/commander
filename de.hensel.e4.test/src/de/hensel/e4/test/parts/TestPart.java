package de.hensel.e4.test.parts;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;

import de.hensel.e4.test.file.FileReaderIO;

public class TestPart {
	private Composite composite;
	private TreeViewer treeViewer;
	private IContentProvider contentProvider;
	public static final String ID = "de.hensel.e4.test.part.1";
	private Text text_1;
	private Composite composite_1;
	private Text text_2;
	private Composite composite_2;
	private Composite composite_3;
	
	private FileReaderIO reader;
	
	public TestPart() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		reader = new FileReaderIO();
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		treeViewer = new TreeViewer(scrolledComposite, SWT.BORDER);
		treeViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element){
				return ((String)element).toString();
				
			}
		});
		treeViewer.setContentProvider(new FileTreeContentProvider());
		treeViewer.setLabelProvider(new FileTreeLabelProvider());
		treeViewer.setInput(new File("C:"));
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				if(event.getSelection() instanceof TreeSelection){
					if(((TreeSelection)event.getSelection()).getFirstElement() instanceof File){
						File firstElement = (File)((TreeSelection)event.getSelection()).getFirstElement();
						setText(reader.readFile(firstElement));
					}
						
				}
				
			}
		});
//		String[] content = new String[]{"Test1","Test2"};
		
//		Tree tree = treeViewer.getTree();
		
		scrolledComposite.setContent(treeViewer.getTree());
						
						composite_3 = new Composite(composite, SWT.NONE);
						composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
						
						composite_1 = new Composite(composite_3, SWT.NONE);
						GridLayout gl_composite_1 = new GridLayout(1, false);
						gl_composite_1.horizontalSpacing = 3;
						composite_1.setLayout(gl_composite_1);
						
						composite_2 = new Composite(composite_1, SWT.NONE);
						GridData gd = new GridData(SWT.FILL, SWT.FILL, true,true, 1, 1);
						gd.heightHint = 30;
						gd.minimumHeight = 30;
						composite_2.setLayoutData(gd);
						
						Label lblSearch = new Label(composite_2, SWT.NONE);
						lblSearch.setBounds(0, 3, 37, 13);
						lblSearch.setText("Search:");
						
						text_2 = new Text(composite_2, SWT.BORDER);
						text_2.setBounds(43, 0, 167, 19);
						
						text_1 = new Text(composite_1, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
						GridData gd_text_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
						gd_text_1.heightHint = 232;
//						gd_text_1.widthHint = 169;
						text_1.setLayoutData(gd_text_1);
						
						ProgressBar progressBar = new ProgressBar(composite_1, SWT.NONE);
						GridData gd_progressBar = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
//						gd_progressBar.widthHint = 213;
						gd_progressBar.heightHint = 20;
						gd_progressBar.minimumHeight = 20;
						progressBar.setLayoutData(gd_progressBar);
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO	Set the focus to control
	}
	
	public void setText(String text){
		text_1.setText(text);
//		text_2.setText(text);
	}
	
	public Text getTextField(){
		return text_1;
	}
	
	class FileTreeContentProvider implements ITreeContentProvider {
		  public Object[] getChildren(Object arg0) {
		    return ((File) arg0).listFiles();
		  }

		  public Object getParent(Object arg0) {
		    return ((File) arg0).getParentFile();
		  }

		  public boolean hasChildren(Object arg0) {
		    Object[] obj = getChildren(arg0);
		    return obj == null ? false : obj.length > 0;
		  }

		  public Object[] getElements(Object arg0) {
		    return File.listRoots();
		  }

		  public void dispose() {
		  }

		  public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		  }
		}
	
	class FileTreeLabelProvider implements ILabelProvider {
		  private List listeners;

		  private Image file;

		  private Image dir;

		  public FileTreeLabelProvider() {
			listeners = new ArrayList();

			URL url = getClass().getResource("/icons/");
			
			file = new Image(null, getClass().getResourceAsStream(
					"/icons/6.png"));
			dir = new Image(null, getClass().getResourceAsStream(
					"/icons/3.png"));
		}

		  public Image getImage(Object arg0) {
		    return ((File) arg0).isDirectory() ? dir : file;
		  }

		  public String getText(Object arg0) {
		    String text = ((File) arg0).getName();

		    if (((File) arg0).getName().length() == 0) {
		      text = ((File) arg0).getPath();
		    }
		    return text;
		  }

		  public void addListener(ILabelProviderListener arg0) {
		    listeners.add(arg0);
		  }

		  public void dispose() {
		    // Dispose the images
		    if (dir != null)
		      dir.dispose();
		    if (file != null)
		      file.dispose();
		  }

		  public boolean isLabelProperty(Object arg0, String arg1) {
		    return false;
		  }

		  public void removeListener(ILabelProviderListener arg0) {
		    listeners.remove(arg0);
		  }
		}
}
