package org.pivot4j.analytics.ui.chart;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.olap4j.metadata.Member;
import org.pivot4j.ui.AbstractRenderCallback;
import org.pivot4j.ui.chart.ChartRenderContext;
import org.pivot4j.ui.command.UICommand;
import org.primefaces.component.breadcrumb.BreadCrumb;
import org.primefaces.component.chart.UIChart;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.model.chart.BubbleChartSeries;
import org.primefaces.model.chart.CartesianChartModel;


public abstract class AbstractBubbleChartBuilder<C extends UIChart,B extends BubbleChartSeries>
        extends AbstractChartBuilder<C, CartesianChartModel> implements
                ChartBuilder  {

	public AbstractBubbleChartBuilder(FacesContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private FacesContext context;

	private UIComponent component;

	private HtmlPanelGrid pageComponent;

	private C chart;

	private B ChartSeries;



	/**
	 * @return the context
	 */
	protected FacesContext getContext() {
		return context;
	}

	/**
	 * @return the component
	 */
	public UIComponent getComponent() {
		return component;
	}

	/**
	 * @param component
	 *            the component to set
	 */
	public void setComponent(UIComponent component) {
		this.component = component;
	}

	/**
	 * @return the pageComponent
	 */
	protected HtmlPanelGrid getPageComponent() {
		return pageComponent;
	}

	/**
	 * @return the chart
	 */
	protected C getChart() {
		return chart;
	}

	/**
	 * @return the ChartSeries
	 */
	protected B getChartSeries() {
		
		getChartSeries().setRadius(20);
		getChartSeries().setLabel("ABC");
		
		return ChartSeries;
	}

	/**
	 * @see org.pivot4j.ui.AbstractRenderCallback#startRender(org.pivot4j.ui.RenderContext)
	 */
	@Override
	public void startRender(ChartRenderContext context) {
		component.getChildren().clear();
	}

	/**
	 * @see org.pivot4j.ui.chart.ChartRenderCallback#startPage(org.pivot4j.ui.chart.ChartRenderContext)
	 */
	@Override
	public void startPage(ChartRenderContext context) {
		this.pageComponent = createPageComponent(context);
	}

	/**
	 * @see org.pivot4j.ui.chart.ChartRenderCallback#startChart(org.pivot4j.ui.chart.ChartRenderContext)
	 */
	@Override
	public void startChart(ChartRenderContext context) {
		System.out.println("$$ AbstractBubbleChartBuilder ChartRenderContext");
		this.chart = createChart(context);
		this.ChartSeries = ChartSeries(context);

		configureChart(context, chart);
	}

	protected abstract C createChart(ChartRenderContext context);

	protected abstract B ChartSeries(ChartRenderContext context);

	/**
	 * @param context
	 * @return
	 */
	protected HtmlPanelGrid createPageComponent(ChartRenderContext context) {
		DefaultChartRenderer renderer = (DefaultChartRenderer) context
				.getRenderer();

		HtmlPanelGrid grid = new HtmlPanelGrid();

		if (renderer.getWidth() <= 0) {
			grid.setStyle("width: 100%;");
		}

		grid.setStyleClass("chart-page");
		grid.setColumns(context.getChartCount());

		return grid;
	}

	/**
	 * @param context
	 * @param chart
	 */
	protected void configureChart(ChartRenderContext context, C chart) {
		List<Member> path = context.getChartPath();

		if (path != null && path.size() > 0) {
			String title = path.get(path.size() - 1).getCaption();

			chart.setTitle(title);

			System.out.println("configureChart " + ":" + title );
			
		}

		chart.setShadow(true);

		DefaultChartRenderer renderer = (DefaultChartRenderer) context
				.getRenderer();

		if (renderer.getLegendPosition() != null) {
			chart.setLegendPosition(renderer.getLegendPosition().name());
		}

		chart.setXaxisAngle(renderer.getXAxisAngle());
		chart.setYaxisAngle(renderer.getYAxisAngle());

		
		StringBuilder builder = new StringBuilder();
		builder.append("width: ");

		if (renderer.getWidth() <= 0) {
			builder.append("100%; ");
		} else {
			builder.append(Integer.toString(renderer.getWidth()));
			builder.append("px; ");
		}

		if (renderer.getHeight() > 0) {
			builder.append("height: ");
			builder.append(Integer.toString(renderer.getHeight()));
			builder.append("px;");
		}

		chart.setStyle(builder.toString());
	}

	/**
	 * @see org.pivot4j.ui.chart.ChartRenderCallback#startSeries(org.pivot4j.ui.chart.ChartRenderContext)
	 */
	@Override
	public void startSeries(ChartRenderContext context) {
	}

	/**
	 * @see org.pivot4j.ui.RenderCallback#renderCommands(org.pivot4j.ui.RenderContext,
	 *      java.util.List)
	 */
	@Override
	public void renderCommands(ChartRenderContext context,
			List<UICommand<?>> commands) {
	}

	/**
	 * @see org.pivot4j.ui.chart.ChartRenderCallback#endSeries(org.pivot4j.ui.chart.ChartRenderContext)
	 */
	@Override
	public void endSeries(ChartRenderContext context) {
	}

	/**
	 * @see org.pivot4j.ui.chart.ChartRenderCallback#endChart(org.pivot4j.ui.chart.ChartRenderContext)
	 */
	@Override
	public void endChart(ChartRenderContext context) {
		chart.setValue(ChartSeries);

		pageComponent.getChildren().add(chart);

		this.ChartSeries = null;
		this.chart = null;
	}
	
	/**
	 * @see org.pivot4j.analytics.ui.chart.AbstractChartBuilder#createModel(org.pivot4j.ui.chart.ChartRenderContext)
	 */
	@Override
	protected CartesianChartModel createModel(ChartRenderContext context) {
		return new CartesianChartModel();
	}

}