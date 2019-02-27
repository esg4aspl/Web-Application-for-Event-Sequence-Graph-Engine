//create dashed ellipse

function mxDashedEllipse(bounds, fill, stroke, strokewidth){
	
	mxShape.call(this);
	this.bounds = bounds;
	this.fill   = fill;
	this.stroke = stroke;
	this.strokewidth = (strokewidth != null) ? strokewidth : 2;

	
};

mxUtils.extend(mxDashedEllipse, mxShape);


mxDashedEllipse.prototype.paintVertexShape = function(c, x, y, w, h)
{
	
	c.ellipse(x, y, w, h);
	c.setDashed(true);
	c.fillAndStroke();
	
	
};
