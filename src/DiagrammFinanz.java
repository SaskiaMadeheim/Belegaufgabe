import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class DiagrammFinanz extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private int histogramHeight = 300;
    private int barWidth = 100;
    private int barGap = 10;

    private JPanel barPanel;
    private JPanel labelPanel;

    private List<Bar> bars = new ArrayList<Bar>();

    public DiagrammFinanz()
    {
        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        //Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
       // Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( inner );
        barPanel.setBackground(Color.WHITE);

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );
        labelPanel.setBackground(Color.WHITE);

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }

    public void addHistogramColumn(String label, double value, Color color)
    {
        Bar bar = new Bar(label, value, color);
        bars.add( bar );
    }

    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();

        int maxValue = 0;

        for (Bar bar: bars)
            maxValue = (int) Math.max(maxValue, bar.getValue());

        for (Bar bar: bars)
        {
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.setForeground(new Color(47,85,178));
            int barHeight = (int) ((bar.getValue() * histogramHeight) / maxValue);
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            barLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            barLabel.setForeground(new Color(47,85,178));
            labelPanel.add( barLabel );
        }
    }

    private class Bar
    {
        private String label;
        private double value;
        private Color color;

        public Bar(String label, double value, Color color)
        {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel()
        {
            return label;
        }

        public double getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }
    }

    private class ColorIcon implements Icon
    {
        private int shadow = 0;

        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, int height)
        {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y)
        {
            g.setColor(color);
            g.fillRect(x, y, width - shadow, height);
            g.setColor(Color.white);
            g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
        }
    }

    public static JPanel createAndShowGUI(double ein, double aus)
    {
        DiagrammFinanz panel = new DiagrammFinanz();
        panel.addHistogramColumn("Einnahmen", ein, Color.GREEN);
        panel.addHistogramColumn("Ausgaben", aus, Color.RED);
        panel.layoutHistogram();
        
        panel.setBackground(Color.white);
        
        return panel;

    }

}