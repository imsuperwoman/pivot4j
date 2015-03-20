package org.pivot4j.analytics.ui.chart;


import javax.faces.context.FacesContext;

import org.pivot4j.ui.chart.ChartRenderContext;
import org.primefaces.component.chart.bubble.BubbleChart;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;

public  class BubbleChartBuilder extends  AbstractBubbleChartBuilder<BubbleChart, BubbleChartSeries>  {

   private  BubbleChartSeries BubbleChartSeries ;
   public static String NAME = "BubbleChart";
   /**
	 * @param context
	 */
	public BubbleChartBuilder(FacesContext context) {
		super(context);	
	}

	/**
	 * @see org.pivot4j.analytics.ui.chart.ChartBuilder#getName()
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * @see org.pivot4j.analytics.ui.chart.AbstractChartBuilder#createChart(org.pivot4j.ui.chart.ChartRenderContext)
	 */
	@Override
	protected BubbleChart createChart(ChartRenderContext context) {
		
		return new BubbleChart();
	}
	
	/**
	 * @see org.pivot4j.analytics.ui.chart.AbstractChartBuilder#configureChart(org.pivot4j.ui.chart.ChartRenderContext,
	 *      org.primefaces.component.chart.UIChart)
	 */
	@Override
	protected void configureChart(ChartRenderContext context, BubbleChart chart) {
		super.configureChart(context, chart);
		chart.setShadow(false);
		chart.setBubbleGradients(true);
		chart.setBubbleAlpha(0.8);
         	
	}

	
	@Override
	public void renderContent(ChartRenderContext context, String label,
			Double value) {
		// TODO Auto-generated method stub

		getBubbleModel().setTitle("Bubble Chart");

		
		System.out.println("$$$$ renderContent" + context.getMember() + label + value);	
	}
	
	public BubbleChartModel getBubbleModel(){ 
		
		return new BubbleChartModel(); 
		}


	@Override
	protected BubbleChartModel createModel(ChartRenderContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BubbleChartSeries ChartSeries(ChartRenderContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
