package com.ardublock.translator.block.Duinoedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Digital_Tube_Ecrire  extends TranslatorBlock {

	public Digital_Tube_Ecrire (Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	//@Override
		public String toCode() throws SocketNullException, SubroutineNotDeclaredException
		{
			String Display;
			String Clk;
			String Dio;
			String Brightness;
			TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
			Clk = translatorBlock.toCode();
			translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
			Dio = translatorBlock.toCode();
			translatorBlock = this.getRequiredTranslatorBlockAtSocket(2);
			Brightness = translatorBlock.toCode();
			translatorBlock = this.getRequiredTranslatorBlockAtSocket(3);
			Display = translatorBlock.toCode();
			
			translator.addHeaderFile("TM1637.h");
			translator.addDefinitionCommand("//libraries at http://www.duinoedu.com/ \n// Pin Led Bar\n"
					+ "TM1637 mesChiffres"+Clk+Dio+"(" + Clk
					+ "," + Dio + ");");
			translator.addSetupCommand("mesChiffres"+Clk+Dio+".brancher();\n"+
					"mesChiffres"+Clk+Dio+".luminosite("+Brightness +");\n");
			String ret;
			ret =  "mesChiffres"+Clk+Dio+".ecrire("+Display +");";
		

			return ret ;	
		}
}
