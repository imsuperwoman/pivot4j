/*
 * ====================================================================
 * This software is subject to the terms of the Common Public License
 * Agreement, available at the following URL:
 *   http://www.opensource.org/licenses/cpl.html .
 * You must accept the terms of that agreement to use this software.
 * ====================================================================
 */
package org.pivot4j.ui;

import org.pivot4j.PivotModel;
import org.pivot4j.datasource.SimpleOlapDataSource;
import org.pivot4j.impl.PivotModelImpl;
import org.pivot4j.ui.table.TableRenderContext;
import org.pivot4j.ui.table.TableRenderer;

public abstract class AbstractMockRenderTestCase {

	protected TableRenderContext createDummyRenderContext() {
		PivotModel model = new PivotModelImpl(new SimpleOlapDataSource());
		return new TableRenderContext(model, new TableRenderer(), 5, 10, 2, 2);
	}
}
