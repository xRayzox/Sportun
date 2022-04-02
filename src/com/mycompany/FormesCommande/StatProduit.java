/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.FormesCommande;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.my.entites.Produit;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class StatProduit {
    Resources theme ; 

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(50);

        renderer.setLegendTextSize(50);
        renderer.setMargins(new int[]{20, 30, 15, 0});

        renderer.setLabelsColor(ColorUtil.BLACK);

        for (int color : colors) {

            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, ArrayList<Produit> ab) {
        CategorySeries series = new CategorySeries(title);

        for (Produit u : ab) {
            series.add(u.getName(), u.getQuantity());

        }

        return series;
    }

    
    
    public void createPieChartForm(String titre, ArrayList<Produit> ab,Resources res) {
        // Generate the values
        
        theme = UIManager.initFirstTheme("/themeCoHeal");
        int size = ab.size();
        double[] values = new double[size];
        int i = 0;
        for (Produit u : ab) {
            values[i] = u.getQuantity();
            i++;
        }

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.rgb(255,102,1),ColorUtil.rgb(255,152,1),ColorUtil.rgb(255,142,1),ColorUtil.rgb(205,192,1), ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN, ColorUtil.BLACK};

        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset(titre, ab), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form("STATISTIQUES",
                new BorderLayout());
  
        f.add(BorderLayout.CENTER, c);
        
         
        f.getToolbar().addCommandToLeftBar("back", null, (evt)
                -> {
            
              new NewsfeedFormC(theme).showBack();
            
            
        });
        
        
        f.show();

    }
    
}
