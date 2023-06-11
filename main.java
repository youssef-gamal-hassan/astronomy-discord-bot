import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class main extends ListenerAdapter  {
    public static void updateCommands(JDA jda){
        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(Commands.slash("moon-phase", "Today's Moon Phase").addOptions(new OptionData(OptionType.STRING, "moon-style", "pick your preferred moon style", true).addChoice("default", "default").addChoice("sketch", "sketch").addChoice("shaded", "shaded")));
        commands.addCommands(Commands.slash("star-chart", "Returns star chart of requested constellation").addOptions(new OptionData(OptionType.STRING, "constellation-name", "choose the constellation you want to view", true).addChoice("Andromeda","and").addChoice("Antlia","ant").addChoice("Apus","aps").addChoice("Aquarius","aqr").addChoice("Aquila","aql").addChoice("Ara","ara").addChoice("Aries","ari").addChoice("Auriga","aur").addChoice("Boötes","boo").addChoice("Caelum","cae").addChoice("Camelopardalis","cam").addChoice("Cancer","cnc").addChoice("Canes Venatici","cvn").addChoice("Canis Major","cma").addChoice("Canis Minor","cmi").addChoice("Capricornus","cap").addChoice("Carina","car").addChoice("Cassiopeia","cas").addChoice("Centaurus","cen").addChoice("Cepheus","cep").addChoice("Cetus","cet").addChoice("Chamaeleon","cha"))
                .addOptions(new OptionData(OptionType.STRING, "chart-style", "pick your preffered star chart style", true).addChoice("Default","default").addChoice("Inverted","inverted").addChoice("Navy","navy").addChoice("Red","red")));
        commands.queue();
        System.out.println("Commands Updated!");
    }
    public String moonHttpRequest(String moonStyle){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new JSONObject(new MoonPhase(new MoonStyle(moonStyle, "stars", "black", "white", "white"), new MoonObserver(30.064286239773992f, 31.493958476697806f, "2023-06-10"))).toString());
        Request request = new Request.Builder().url("https://api.astronomyapi.com/api/v2/studio/moon-phase").addHeader("Authorization", "Bearer " + System.getenv("moonAPI")).post(body).build();
        return getImage(client, request);
    }

    public String starHttpRequest(String constellation, String style){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new JSONObject(new StarChart(style, constellation, new MoonObserver(30.064286239773992f, 31.493958476697806f, "2023-06-10"))).toString());
        Request request = new Request.Builder().url("https://api.astronomyapi.com/api/v2/studio/star-chart").addHeader("Authorization", "Bearer " + System.getenv("moonAPI")).post(body).build();
        return getImage(client, request);
    }

    private String getImage(OkHttpClient client, Request request) {
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            JSONObject obj = new JSONObject(response.body().string());
            String image = obj.getJSONObject("data").getString("imageUrl");
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws InterruptedException {

        JDA jda = JDABuilder.createDefault(System.getenv("token"))
                .addEventListeners(new main())
                .enableIntents(GatewayIntent.GUILD_VOICE_STATES)
                .setActivity(Activity.watching("The Stars"))
                .build();

        jda.awaitReady();
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "moon-phase":
                if (event.getOption("moon-style") != null){
                    event.reply(moonHttpRequest(event.getOption("moon-style").getAsString())).queue();
                }
                else {
                    event.reply(moonHttpRequest("default")).queue();
                }
                break;
            case "star-chart":
                try {
                    event.reply(starHttpRequest(event.getOption("constellation-name").getAsString(), event.getOption("chart-style").getAsString()).toString()).queue();
                }catch (Exception e){
                    event.reply("something went wrong").queue();
                }
                break;
            default:
                event.reply("What the fuck?!").queue();
        }
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if (event.getChannelJoined() == null){
            return;
        }
        switch(event.getMember().getId()){
            case "462753867449237506":
                event.getGuild().getTextChannelById("1105589927451820072").sendMessage("JMT El Kalb Da5al el VC").queue();
                break;
            case "321367134699520000":
                event.getGuild().getTextChannelById("1105589927451820072").sendMessage("ABO MOZE 7ADAR").queue();
                break;
            case "932010472268505159":
                event.getGuild().getTextChannelById("1105589927451820072").sendMessage("EWWWW LARA").queue();
                break;
            default:
                return;

        }
    }

}
